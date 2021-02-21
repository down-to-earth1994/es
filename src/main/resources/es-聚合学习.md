1： es 基本数据类型  
 ```
  字段类型               es 类型            说明
  1：string,varchar        keyword         不可标记文本字段
  2：string,varchar,text    text           要标记文本字段
  3：integer               integer         这是一个整数
  4：long                  long            这是一个长整数
  5：float                 float           这是一个浮点数
  6：double                double          这是一个double 类型浮点数
  7：boolean               boolean         这是一个布尔值  true 或false 
  8：date/datetime         date            这是一个日期时间值
  9：byte/binary           binary           二进制数据的字节，例如 文件或字节流
 
根据数据类型，可以在处理字段时候为es 赋予明确的指令
1：store   (默认为false)  该选项字段标记我为存储在单独的索引片段中以便快速搜索
2：index  定义是否应为字段建立索引，此参数可能为true 或者false  索引字段不可搜索（默认为true） 
3：null_value  如果字段为null ,则定义一个默认值
4：boost  用于更改字段的重要性 （默认为1.0)
   boost 仅在词 （Term） 级别上工作，因此它主要用于词条和匹配查询
5:search_analyzer  定义类要在搜索过程中使用分析器，如果未定义则使用 父对象的分析器（null）
6:analyser 设置要使用的默认分析器（默认为null）
7:include_in_all 这会将当前字段 标记为在特殊_all 字段
8：norms ：控制lucene 规范，此参数可用于更改地对查询进行评分
9：copy_to: 允许你将一个字段内容复制到另一个字段以实现功能
10：ignore_above 这使你可以跳过大于其值的索引字符串
 ```
