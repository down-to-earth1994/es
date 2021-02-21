elasticsearch  version   5.6.12  
elasticsearch  部署的时候注意点
    transport.host 有注释记得去掉  
    cluster.name   代码中配置记得保持一致 elasticsearch.yml  
    -e ES_JAVA_OPTS="-Xms256m -Xmx256m"  记得控制内存 默认是2G 修改/etc/jvm.options  
 Reindex 思考  
   github 
   https://github.com/karussell/elasticsearch-reindex  
 es reindex 数据迁移 参考博客
    https://blog.csdn.net/chogzyo/article/details/80719766?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-3.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-3.control  
 参考命令
     POST _reindex
     {
       "source": {
         "index": "twitter"
       },
       "dest": {
         "index": "new1"
       },
       "script": {
         "inline": """
         ctx._id = ctx._type + "-" + ctx._id;
         ctx._source.type = ctx._type;
         ctx._type = "doc";
         """,
         "lang": "painless"
       }
     }  
 reindex 超时解决方案
  https://blog.csdn.net/u014646662/article/details/97638792?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control  
 esdump 使用命令
https://www.cnblogs.com/yfb918/p/10735041.html  

####esm
    https://github.com/medcl/esm
####elasticdump 安装
##### 安装node
    wget http://nodejs.org/dist/v0.10.32/node-v0.10.32-linux-x64.tar.gz
    tar zxvf node-v0.10.32-linux-x64.tar.gz
    mv  node-v0.10.32-linux-x64 /usr/local/node   #重命名
    cd /usr/bin    #切换到环境变量目录下
    ln -s /usr/local/node/bin/node node   #添加执行软链
    ln -s /usr/local/node/bin/npm npm
    node -v                #查看版本，验证是否安装成功
    npm-v
    sudo npm i -g n  #安装 node 版本控制
    sudo n stable    #安装稳定版本
    sudo n v8.11.2   #安装指定版本
    sudo n latest    #安装最新版本
    npm i -g npm@6.14.2   # 升级npm 到指定的版本
    npm config set registry http://registry.npm.taobao.org/  切换到淘宝镜像
    
    npm install elasticdump -g  #安装elasticdump node 包
    elasticdump --version  # 查看安装的版本
    
##### 安装
####备份分词器：
  elasticdump  --input=http://127.0.0.1:9200/ceshi --output=/Users/heyanfeng/Desktop/es_install/es_data/mapping/ceshi.json --type=analyzer  
####备份 索引：
 elasticdump  --ignore-errors=true  --scrollTime=120m  --bulk=true --input=http://127.0.0.1:9200/ceshi --output=/Users/heyanfeng/Desktop/es_install/es_data/mapping/ceshi.json --type=mapping  
 elasticdump  --ignore-errors=true  --scrollTime=120m  --bulk=true --input=http://10.0.3.123:9200/wxjk_dev_recordinfo202101 --output=/home/es_dump/backup/wxjk/mapping.json --type=mapping 
####备份 data：
   elasticdump --ignore-errors=true  --scrollTime=120m  --bulk=true  --input=http://127.0.0.1:9200/ceshi --output=/Users/heyanfeng/Desktop/es_install/es_data/data/ceshi_data.json --type=data  
   elasticdump  --ignore-errors=true  --scrollTime=120m  --bulk=true --input=http://10.0.3.123:9200/wxjk_dev_recordinfo202101 --output=/home/es_dump/backup/wxjk/data.json --type=data
####导入: 
  elasticdump  --input=/Users/heyanfeng/Desktop/es_install/es_data/data/ceshi_data.json --output=http://127.0.0.1:9200/ceshi1  
  
  谷歌翻译英文源Elasticsearch实例，即：http：//本地主机：9200 
  -q，-query =针对源Elasticsearch实例的查询，在迁移之前过滤数据，即：名称：medcl 
  -d，-dest =目标Elasticsearch实例，即：http：//本地主机：9201
   -m，-source_auth =源Elasticsearch实例的基本身份验证，即：用户：pass 
   -n，-dest_auth =目标Elasticsearch实例的基本身份验证，即：user：pass 
   - c ，-count =一次一次的文档数：即滚动请求中的“大小”（10000）
   -buffer_count =内存中缓冲的文档数（100000）
   -w，-workers =批量工作程序的并发数（1 ）- b，
    --bulk_size =批量大小（MB）（5）
   -t，-time =滚动时间（1m）-sliced_scroll_size =切片滚动的大小，要正常工作，该大小应>1（1）
   -f，--强制复制前删除目标索引g 
   -a，-所有以。开头的副本索引和_ 
        --copy_settings从源复制索引设置--copy_mappings从源-碎片=复制索引映射在新创建的索引上设置多个分片-x，
        -src_indexes =要复制的索引名称，支持正则表达式和逗号分隔列表（_all）-y，
        -dest_index =保存索引名称，仅允许一个索引名称，如果未指定则将使用原始索引名称-u，
        -type_override =覆盖类型名称--green等待两个主机的状态状态变为绿色倾倒。否则黄色是可以的-v，
        -log =设置日志等级，选项：trace，debug，info，warn，error（INFO）-o，
        -output_file =将源索引的文档输出到本地文件中-i，
        -input_file =从本地转储文件建立索引
        --input_file_type =输入文件的数据类型，选项：转储，json_line，json_array，log_line（转储）
        -source_proxy =将代理设置为源http连接，即：http：//127.0 .0.1：8080 
        --dest_proxy =将代理设置为目标http连接，即：http：//127.0.0.1：8080-迁移完成后刷新刷新--fields =过滤源串联，以逗号分隔，即：col1，col2 ，col3。 .. 
        --rename =重命名源标题，用逗号分隔，即：_type：类型，名称：myname -l
        ，-logstash_endpoint =目标logstash tcp端点，即：127.0.0.1：5055
         --TLS的--secured_logstash_endpoint目标日志存储tcp端点是安全的
         --repeat_times =将源中的数据重复N次到达目标输出，使用与参数regenerate_id对齐来放大数据大小-r，
         -regenerate_id为文档重新生成ID ，，这将覆盖数据源中存在的文档ID--compressinggzip压缩流量-p，
  -sleep =睡眠在完成批量请求后N秒（-1）帮助选项：-h，-help显示此帮助消息
