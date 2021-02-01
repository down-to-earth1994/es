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
