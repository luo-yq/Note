
关系型数据库遵循ACID规则，事务有如下四个特性：
1、A (Atomicity) 原子性
原子性很容易理解，也就是说事务里的所有操作要么全部做完，要么都不做，事务成功的条件是事务里的所有操作都成功，只要有一个操作失败，整个事务就失败，需要回滚。
2、C (Consistency) 一致性
一致性也比较容易理解，也就是说数据库要一直处于一致的状态，事务的运行不会改变数据库原本的一致性约束。
3、I (Isolation) 独立性
所谓的独立性是指并发的事务之间不会互相影响，如果一个事务要访问的数据正在被另外一个事务修改，只要另外一个事务未提交，它所访问的数据就不受未提交事务的影响。
4、D (Durability) 持久性
持久性是指一旦事务提交后，它所做的修改将会永久的保存在数据库上，即使出现宕机也不会丢失。



数据库（Database）：数据库是带有相关数据的表的集合。
表（Table）：表是带有数据的矩阵。数据库中的表就像一种简单的电子表格。
列（Column）：每一列（数据元素）都包含着同种类型的数据，比如邮编。
行（Row）：行（又被称为元组、项或记录）是一组相关数据，比如有关订阅量的数据。
冗余（Redundancy）：存储两次数据，以便使系统更快速。
主键（Primary Key）：主键是唯一的。同一张表中不允许出现同样两个键值。一个键值只对应着一行。
外键（Foreign Key）：用于连接两张表。
复合键（Compound Key）：复合键（又称组合键）是一种由多列组成的键，因为一列并不足以确定唯一性。
索引（Index）：它在数据库中的作用就像书后的索引一样。
引用完整性（Referential Integrity）：用来确保外键一直指向已存在的一行。

1.DBMS 数据库管理系统，数据库软件，如mysql，oracle
2.DB 数据库
3.Table 表
4.Scheme 数据库和表的布局及特性的信息，有时指数据库

表的类型：InnoDB,MyISAM,MERGE
MyISAM表(TYPE=MYISAM)是ISAM类型的一种延伸，具有很多优化和增强的特性。是MySQL的默认表类型。
MyISAM优化了压缩比例和速度，并且可以很方便的在不同的操作系统和平台之间进行移植。
MyISAM支持大表文件(大于4G)允许对BLOB和TEXT列进行索引，支持使用键前缀和使用完整的键搜索记录
表数据和表索引文件可以依存在不同的位置，甚至是不同的文件系统中。
即使是具有相当多的插入、更新和删除操作的表，智能防碎片逻辑也能保证其高性能的协作性。
 
InnoDB表(TYPE=INNODB)，是一个完全兼容ACID(事务的原子性、一致性、独立性及持久性)的、高效率的表完全支持MySQL的事务处理并且不会btwagkyaakftntce。
精细的(行级和表级)锁提高了MySQL事务处理的带走度，同时其也支持无锁定读操作(以前只在Oracle中包含)和多版本的特性。
异步输入／输出和一系列的读缓冲将提高数据检索速度，同时可以进行文件的优化和内存的管理。需要的基础上支持自动在内存上创建散列索引来提高性能，
使用缓冲来提高可靠性和数据库操作的速度。InnoDB表的恨不能可以和MyISAM相媲美，甚至已经超过了MyISAM。
在不同的操作系统和体系结构上是完全可移植的。由于一直处于一致的状态(MySQL通过在启动时检查错误并修复错误来使它们更加健壮)。
对外键、提交、回滚和前滚的操作的支持，使其成为MySQL中最完善的表格式。

MERGE表(TYPE=MERGE)是通过把多个MyISAM表组合到一个单独的表来创建的一种虚拟表。
只有涉及到的表具有完全相同的表结构时才能对表进行组合。字段类型或者索引的任何不同都不能进行成功的结合。 
 
HEAP表(TYPE=HEAP)是内存中的表，它使用能够比较快速的散列索引(当运行INSERT查询时，独立评价指出HEAP表最少比MyISAM表快30％)，因此，对于临时表可以优化。
存储在里面的数据只在MySQL服务器的生命期内存在，如果MySQL服务器崩溃或者被关掉，都会使其中的数据消失不见。虽然HEAP表具有性能方面的好处，
但是由于它的临时性和一些其他功能限制，在实际中不可能经常使用。
HEAP表的大小只受到系统上可用内存的限制，MySQL是很聪明的，其具有内建保护来阻止无意识地使用所有可用内存。
所以我们不用担心内存会被HEAP表用尽。HEAP表不支持BLOB或TEXT列，不能超过max_heap_table_size变量指定的大小。
 
ISAM表(TYPE=ISAM)和MyISAM表相似，但是各方面都不如MyISAM  

BerkeleyDB表(TYPE=BDB)（InnoDB格式很大程度上可以取代BerkeleyDB格式）是为了满足MySQL开发者对事务安全表日益增长的需求而发展起来的。
BerkeleyDB表具有很多有趣的鹅，包括提交和回滚操作、多用户并发访问、检查点、次要索引、通过日志恢复崩溃、连续地和键控地访问数据等， 
它的移动比较困难(在创建时，表路径硬编码在表文件中)不能压缩表索引，而且其表通常比MyISAM相应的表要大
 
 

在 MySQL 中，事务通常以 BEGIN WORK 语句开始，以 COMMIT 或 ROLLBACK（只取其一） 语句结束。在开始与结束声明之间的 SQL 命令就构成了事务的主体。

通过设定会话变量 AUTOCOMMIT 可以控制事务行为。
如果 AUTOCOMMIT 被设为1（默认值），则每一个 SQL 语句（无论是否在事务中）都会被认为是一个完成的事务，则默认当它结束时予以提交。
当 AUTOCOMMIT 被设为0（通过命令 SET AUTOCOMMIT=0）时，后续一系列语句就像是一个事务，直到 COMMIT 语句执行为止，不再提交任何行为。


MySQL 中的事务安全型表类型
不能直接使用事务，如果强行使用，则无法保证它们的安全性。如果打算在 MySQL 编程中使用事务，就需要以特殊的方式来创建表。有很多种支持事务表可供选择，但其中最常见的是 InnoDB。
对 InnoDB 表的支持，需要在编译 MySQL 源码时用到一个特殊的编译参数。如果 MySQL 版本不支持 InnoDB，则需要请你的 ISP 构建一个支持 InnoDB 表类型的 MySQL 版本，或者下载安装一个用于 Windows 或 Linux/UNIX 系统的 MySQL-Max 二进制分发版，在其开发环境中使用这种表类型。

如果你的 MySQL 版本支持 InnoDB 表，则只需在表创建语句中添加一个 TYPE = InnoDB 定义即可。比如，下面这段代码就创建了一个叫做 tcount_tbl 的 InnoDB 表。
如果你的 MySQL 支持 GEMINI 或 BDB 这两种表类型，也可以使用它们。
 


如果一定要对 AUTO_INCREMENT 列进行重新排序，那么正确的方式是将该列从表中删除，然后再添加它。下面这个范例中就用了这个技巧，在 insect 表中对 id 值重新排序。

mysql> ALTER TABLE insect DROP id;
mysql> ALTER TABLE insect
    -> ADD id INT UNSIGNED NOT NULL AUTO_INCREMENT FIRST,
    -> ADD PRIMARY KEY (id);
以特定值作为序列初始值
MySQL 默认以 1 作为序列初始值，但你也可以在创建表时指定其他的数字。下面的范例以 100 作为序列初始值。
...id INT UNSIGNED NOT NULL AUTO_INCREMENT = 100,
 
