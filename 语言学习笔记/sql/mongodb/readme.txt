1.简介
NoSQL(NoSQL = Not Only SQL )，意即"不仅仅是SQL"。

NoSQL分类
列存储	 Hbase，Cassandra，Hypertable   按列存储数据。最大的特点是方便存储结构化和半结构化数据，方便做数据压缩，对针对某一列或者某几列的查询有非常大的IO优势。
文档存储   MongoDB，CouchDB   文档存储一般用类似json的格式存储，存储的内容是文档型的。这样也就有有机会对某些字段建立索引，实现关系数据库的某些功能。
key-value存储   Tokyo Cabinet / Tyrant，Berkeley DB，MemcacheDB，Redis     可以通过key快速查询到其value。一般存储不管value的格式。（Redis包含了其他功能）
图存储Neo4J，FlockDB   图形关系的最佳存储。使用传统关系数据库来解决的话性能低下，而且设计使用不方便。
对象存储db4o，Versant    通过类似面向对象语言的语法操作数据库，通过对象的方式存取数据。
xml数据库    Berkeley DB XML，BaseX     高效的存储XML数据，并支持XML的内部查询语法，比如XQuery,Xpath。



分布式系统
分布式系统（distributed system）由多台计算机和通信的软件组件通过计算机网络连接（本地网络或广域网）组成。
分布式系统是建立在网络之上的软件系统。正是因为软件的特性，所以分布式系统具有高度的内聚性和透明性。
因此，网络和分布式系统之间的区别更多的在于高层软件（特别是操作系统），而不是硬件。
分布式系统可以应用在在不同的平台上如：Pc、工作站、局域网和广域网上等。

分布式计算的优点
可靠性（容错） ：分布式计算系统中的一个重要的优点是可靠性。一台服务器的系统崩溃并不影响到其余的服务器。
可扩展性：在分布式计算系统可以根据需要增加更多的机器。
资源共享：共享数据是必不可少的应用，如银行，预订系统。
灵活性：由于该系统是非常灵活的，它很容易安装，实施和调试新的服务。
更快的速度：分布式计算系统可以有多台计算机的计算能力，使得它比其他系统有更快的处理速度。
开放系统：由于它是开放的系统，本地或者远程都可以访问到该服务。
更高的性能：相较于集中式计算机网络集群可以提供更高的性能（及更好的性价比）。

分布式计算的缺点
故障排除： ：故障排除和诊断问题。
软件： 更少的软件支持是分布式计算系统的主要缺点。
网络：网络基础设施的问题，包括：传输问题，高负载，信息丢失等。
安全性： 开发系统的特性让分布式计算系统存在着数据的安全性和共享的风险等问题。


CAP定理（CAP theorem）
在计算机科学中, CAP定理（CAP theorem）, 又被称作 布鲁尔定理（Brewer's theorem）, 它指出对于一个分布式计算系统来说，不可能同时满足以下三点:
一致性(Consistency) (所有节点在同一时间具有相同的数据)
可用性(Availability) (保证每个请求不管成功或者失败都有响应)
分隔容忍(Partition tolerance) (系统中任意信息的丢失或失败不会影响系统的继续运作)
CAP理论的核心是：一个分布式系统不可能同时很好的满足一致性，可用性和分区容错性这三个需求，最多只能同时较好的满足两个。
因此，根据 CAP 原理将 NoSQL 数据库分成了满足 CA 原则、满足 CP 原则和满足 AP 原则三 大类：
CA - 单点集群，满足一致性，可用性的系统，通常在可扩展性上不太强大。
CP - 满足一致性，分区容忍必的系统，通常性能不是特别高。
AP - 满足可用性，分区容忍性的系统，通常可能对一致性要求低一些。

RDBMS 
- 高度组织化结构化数据 
- 结构化查询语言（SQL） (SQL) 
- 数据和关系都存储在单独的表中。 
- 数据操纵语言，数据定义语言 
- 严格的一致性
- 基础事务
NoSQL 
- 代表着不仅仅是SQL
- 没有声明性查询语言
- 没有预定义的模式
- 键 - 值对存储，列存储，文档存储，图形数据库
- 最终一致性，而非ACID属性
- 非结构化和不可预知的数据
- CAP定理 
- 高性能，高可用性和可伸缩性

NoSQL的优点/缺点
优点:
- 高可扩展性
- 分布式计算
- 低成本
- 架构的灵活性，半结构化数据
- 没有复杂的关系
缺点:
- 没有标准化
- 有限的查询功能（到目前为止）
- 最终一致是不直观的程序

BASE是NoSQL数据库通常对可用性及一致性的弱要求原则:
Basically Availble --基本可用
Soft-state --软状态/柔性事务。 "Soft state" 可以理解为"无连接"的, 而 "Hard state" 是"面向连接"的
Eventual Consistency --最终一致性 最终一致性， 也是是 ACID 的最终目的。


BSON是一种类json的一种二进制形式的存储格式,简称Binary JSON。


MongoDB 支持如下数据类型：

String：字符串。存储数据常用的数据类型。在 MongoDB 中，UTF-8 编码的字符串才是合法的。
Integer：整型数值。用于存储数值。根据你所采用的服务器，可分为 32 位或 64 位。
Boolean：布尔值。用于存储布尔值（真/假）。
Double：双精度浮点值。用于存储浮点值。
Min/Max keys：将一个值与 BSON（二进制的 JSON）元素的最低值和最高值相对比。
Arrays：用于将数组或列表或多个值存储为一个键。
Timestamp：时间戳。记录文档修改或添加的具体时间。
Object：用于内嵌文档。
Null：用于创建空值。
Symbol：符号。该数据类型基本上等同于字符串类型，但不同的是，它一般用于采用特殊符号类型的语言。
Date：日期时间。用 UNIX 时间格式来存储当前日期或时间。你可以指定自己的日期时间：创建 Date 对象，传入年月日信息。
Object ID：对象 ID。用于创建文档的 ID。
Binary Data：二进制数据。用于存储二进制数据。
Code：代码类型。用于在文档中存储 JavaScript 代码。
Regular expression：正则表达式类型。用于存储正则表达式。

db.test.find({content:{$type:1}})  --显示content为double类型的
$type操作符是基于BSON类型来检索集合中匹配的数据类型，并返回结果。
Double	1	               String	2	           Object	3	         Array	4	        Binary data	5	 
Undefined	6	已废弃。      Object id	  7	       Boolean	8	       Date	9	          Null	10	 
Regular Expression	11	 JavaScript	13	     Symbol	14	       JavaScript (with scope)	15	 
32-bit integer	16	     Timestamp	17	     64-bit integer	18	 
Max key  127  
Min key	255	Query with -1.



聚合函数使用aggregate()
db.COLLECTION_NAME.aggregate(AGGREGATE_OPERATION) 如下根据by_user分组
$sum	计算总和。	db.mycol.aggregate([{$group : {_id : "$by_user", num_tutorial : {$sum : "$likes"}}}])
$avg	计算平均值	db.mycol.aggregate([{$group : {_id : "$by_user", num_tutorial : {$avg : "$likes"}}}])
$min	获取集合中所有文档对应值得最小值。	db.mycol.aggregate([{$group : {_id : "$by_user", num_tutorial : {$min : "$likes"}}}])
$max	获取集合中所有文档对应值得最大值。	db.mycol.aggregate([{$group : {_id : "$by_user", num_tutorial : {$max : "$likes"}}}])
$push	在结果文档中插入值到一个数组中。	db.mycol.aggregate([{$group : {_id : "$by_user", url : {$push: "$url"}}}])
$addToSet	在结果文档中插入值到一个数组中，但不创建副本。	db.mycol.aggregate([{$group : {_id : "$by_user", url : {$addToSet : "$url"}}}])
$first	根据资源文档的排序获取第一个文档数据。	db.mycol.aggregate([{$group : {_id : "$by_user", first_url : {$first : "$url"}}}])
$last	根据资源文档的排序获取最后一个文档数据	db.mycol.aggregate([{$group : {_id : "$by_user", last_url : {$last : "$url"}}}])

MongoDB的聚合管道将MongoDB文档在一个管道处理完毕后将结果传递给下一个管道处理。管道操作是可以重复的。
表达式：处理输入文档并输出。表达式是无状态的，只能用于计算当前聚合管道的文档，不能处理其它的文档。
这里我们介绍一下聚合框架中常用的几个操作：
$project：修改输入文档的结构。可以用来重命名、增加或删除域，也可以用于创建计算结果以及嵌套文档。
$match：用于过滤数据，只输出符合条件的文档。$match使用MongoDB的标准查询操作。
$limit：用来限制MongoDB聚合管道返回的文档数。
$skip：在聚合管道中跳过指定数量的文档，并返回余下的文档。
$unwind：将文档中的某一个数组类型字段拆分成多条，每条包含数组中的一个值。
$group：将集合中的文档分组，可用于统计结果。
$sort：将输入文档排序后输出。
$geoNear：输出接近某一地理位置的有序文档。

管道操作符实例
1、$project实例
db.article.aggregate(
    { $project : {
        title : 1 ,
        author : 1 ,
    }}
 );
这样的话结果中就只还有_id,tilte和author三个字段了，默认情况下_id字段是被包含的，如果要想不包含_id话可以这样:
db.article.aggregate(
    { $project : {
        _id : 0 ,
        title : 1 ,
        author : 1
    }});
2.$match实例
db.articles.aggregate( [
                        { $match : { score : { $gt : 70, $lte : 90 } } },
                        { $group: { _id: null, count: { $sum: 1 } } }
                       ] );
$match用于获取分数大于70小于或等于90记录，然后将符合条件的记录送到下一阶段$group管道操作符进行处理。
3.$skip实例
db.article.aggregate(
    { $skip : 5 });
经过$skip管道操作符处理后，前五个文档被"过滤"掉。



备份恢复
mongodump命令可以通过参数指定导出的数据量级转存的服务器。
>mongodump -h dbhost -d dbname -o dbdirectory   默认目录 mongo/dump
-h：MongDB所在服务器地址，例如：127.0.0.1，当然也可以指定端口号：127.0.0.1:27017

mongodump --host HOST_NAME --port PORT_NUMBER	该命令将备份所有MongoDB数据	mongodump --host w3cschool.cc --port 27017
mongodump --dbpath DB_PATH --out BACKUP_DIRECTORY		mongodump --dbpath /data/db/ --out /data/backup/
mongodump --collection COLLECTION --db DB_NAME	该命令将备份指定数据库的集合。	mongodump --collection mycol --db test

mongodb使用 mongorerstore 命令来恢复备份的数据。
>mongorestore -h dbhost -d dbname --dir dbdirectory --drop：
--drop 恢复的时候，先删除当前数据，然后恢复备份的数据。就是说，恢复后，备份后添加修改的数据都会被删除，慎用哦！

