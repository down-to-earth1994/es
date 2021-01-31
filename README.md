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
