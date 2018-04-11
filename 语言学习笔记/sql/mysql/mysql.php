


MySQL PHP语法

MySQL 可以很好地适用于多种编程语言，比如PERL、C、C++、JAVA，以及 PHP。由于可以开发 Web 应用程序，PHP 是其中最流行的一门语言。

本教程讲解重点在于PHP 环境中使用 MySQL。如果你对使用 PERL 来操作 MySQL 有兴趣，可以参看这个教程：PERL 与 MySQL 教程

PHP 提供了多种能够访问 MySQL 数据库并且操纵数据记录的函数。这些函数的调用方式就跟其他 PHP 函数一样。

PHP 中用于操作 MySQL 的函数一般都采取如下的格式：

mysql_function(value,value,...);

函数名称的第二部分是函数所专有的，通常是一个描述函数行为的词。下面是教程中将会用到的两个函数：

mysqli_connect($connect);
mysqli_query($connect,"SQL statement");
下面这个例子展示的是PHP调用MySQL函数的常见语法格式：




通过 PHP 的 mysql_connect() 函数，可以开启数据库连接。该函数有5个参数。当成功连接后，该函数返回一个 MySQL 连接标识；如连接失败，则返回FALSE。
connection mysql_connect(server,user,passwd,new_link,client_flag);

参数	说明
server	可选参数。运行数据库服务器的主机名。如未指定，则默认值 localhost:3036。
user	可选参数。访问数据库的用户名。如未指定，则默认值为拥有服务器进程的用户名称。
passwd	可选参数。用户访问数据库所用密码。如未指定，则默认没有密码。
new_link	可选参数。如果利用同样的参数第二次调用mysql_connect()，则不会建立新的连接，而是返回已打开连接的标识。
client_flags	可选参数。是由下列常量组合而成：

MYSQL_CLIENT_SSL——使用 SSL 加密。
MYSQL_CLIENT_COMPRESS——使用数据压缩协议。
MYSQL_CLIENT_IGNORE_SPACE——允许函数名后出现空格。
MYSQL_CLIENT_INTERACTIVE——关闭连接之前所空闲等候的交互超时秒数。


通过 PHP 的 mysql_close() 函数，随时可以中断与 MySQL 数据库的连接。该函数只有一个参数，是一个由 mysql_connect()函数所返回的连接。
bool mysql_close ( resource $link_identifier );

如果某个资源未被指定，则最后打开的数据库就会被关闭。如果成功中断连接，该函数返回 true，否则返回 false。



创建 MySQL 数据库

使用 mysqladmin 创建数据库
创建或删除数据库需要拥有特殊的权限。假设你获得了root用户权限，那么利用 mysqladmin 二进制命令可以创建任何数据库。

范例
下面就来创建一个名叫 TUTORIALS 的数据库：

[root@host]# mysqladmin -u root -p create TUTORIALS
Enter password:******
通过上述命令，就创建好了一个名叫 TUTORIALS 的 MySQL 数据库。

利用PHP脚本创建数据库
PHP利用 mysql_query 函数来创建或删除 MySQL 数据库。该函数有2个参数，成功执行操作则返回TRUE，失败则返回FALSE。

语法
bool mysql_query( sql, connection );

参数	说明
sql	必需参数。创建或删除 MySQL 数据库所用的 SQL 命令。
connection	可选参数。如未指定，将使用最后一个由 mysql_connect 打开的连接。
范例
通过下面这个范例来了解如何创建数据库。

<?php
$dbhost = 'localhost:3036';
$dbuser = 'root';
$dbpass = 'rootpassword';
$conn = mysql_connect($dbhost, $dbuser, $dbpass);
if(! $conn )
{
  die('Could not connect: ' . mysql_error());
}
echo 'Connected successfully<br />';
$sql = 'CREATE DATABASE TUTORIALS';
$retval = mysql_query( $sql, $conn );
if(! $retval )
{
  die('Could not create database: ' . mysql_error());
}
echo "Database TUTORIALS created successfully\n";
mysql_close($conn);
?>



MySQL 终止数据库

利用 mysqladmin 删除 MySQL 数据库
同上一节的情况完全一样，创建或删除 MySQL 数据库需要特殊的权限。假如有了 root 用户权限，那就可以用 mysqladmin 二进制命令来随意创建数据库了。

删除数据库要非常谨慎，因为这样做会丢失数据库中所保存的全部数据。

在下面这个范例中，删除了上一节所创建的数据库。

[root@host]# mysqladmin -u root -p drop TUTORIALS
Enter password:******
这时，系统会显示一条警示消息，询问是否确定要删除数据库。

Dropping the database is potentially a very bad thing to do.
Any data stored in the database will be destroyed.

Do you really want to drop the 'TUTORIALS' database [y/N] y
Database "TUTORIALS" dropped
使用 PHP 脚本删除数据库
PHP 使用 mysql_query 函数来创建或删除 MySQL 数据库。该函数包含两个参数，如果成功执行操作，返回 TRUE，否则返回 FALSE。

语法格式
bool mysql_query( sql, connection );

范例
下面这个范例展示了如何删除一个数据库。

<?php
$sql = 'DROP DATABASE TUTORIALS';
$retval = mysql_query( $sql, $conn );
if(! $retval )
{
  die('Could not delete database: ' . mysql_error());
}
echo "Database TUTORIALS deleted successfully\n";
mysql_close($conn);
?>





MySQL 选择数据库

一旦连接上了 MySQL 服务器，就需要选择一个具体的用来运行的数据库。这是因为，有可能会有多个数据库挂接在MySQL服务器上。

利用命令行方式选择 MySQL 数据库
通过 mysql> 提示符来选择数据库是一种非常简单的方法。可以使用 SQL 命令 use 来选择某个数据库。


注意：所有的数据库名称、表名、表字段名都是对大小写敏感的，因此使用SQL命令时，必须要使用正确的名称。
使用PHP脚本选择MySQL数据库
PHP 通过 mysql_select_db 函数来选择数据库。如果成功完成操作，返回 TRUE，否则返回 FALSE。

语法格式
bool mysql_select_db( db_name, connection );

参数	说明
db_name	必需参数。要选择的 MySQL 数据库名称。
connection	可选参数。如未指定，则将使用mysql_connect最后打开的一个连接。
范例
下面这个范例展示如何选择数据库。

<?php
mysql_select_db( 'TUTORIALS' );
mysql_close($conn);
?>



MySQL 数据类型

对于数据库的整体优化来说，正确定义表中的字段是非常关键的。应该只采用字段》。如果事先知道只会用到2个字符的宽度，就不要把字段定义为10个字符宽。字段（或者说列）的类型也被称为数据类型。

MySQL使用的多种数据类型可分为三类：数字、日期与时间，以及字符串类型。

数字类型
MySQL使用标准的 ANSI SQL 数字类型，所以如果你在学习MySQL之前，接触过其他数据库系统，那么肯定对这些定义不会感到陌生。下面就列举出常见的一些数字类型及其说明：

INT 正常大小的整数，可以有符号，也可以没有符号。如果是有符号整数，其允许的取值范围是-2147483648~2147483647；无符号整数的取值范围是从0至4294967295。最高可指定11位数字。
TINYINT 非常小的整数，分为有无符号两种。前有符号时，其允许取值范围是-128~127；无符号时的取值范围为0~255。所以，最高可指定4位数字。
SMALLINT 较小的整数，分为有无符号两种。前有符号时，其允许取值范围是-32768~32767；无符号时的取值范围为0~65535。所以最高可指定5位数字。
MEDIUMINT 中型大小的整数，分为有无符号两种。前有符号时，其允许取值范围是-8388608~8388607；无符号时的取值范围为0~16777215。所以，最高可指定9位数字。
BIGINT 较大型的整数，分为有无符号两种。前有符号时，其允许取值范围为-9223372036854775808~9223372036854775807；无符号时的取值范围为0~18446744073709551615。最高可指定20位数字。
FLOAT(M,D) 不带符号的浮点数。M 代表显示长度，D 代表小数位数。这两个参数都不是必需参数，它们默认为10, 2，表示小数点后有2位数字，而整个数字的位数为10（包含小数位数）。FLOAT 类型的小数精度可以达到24位。
DOUBLE(M,D) 不带符号的双精度浮点数。M 代表显示长度，D 代表小数位数。这两个参数都不是必需参数，它们默认为16, 4，表示小数点后有4位数字，而整个数字的位数为 16（包含小数位数）。DOUBLE 类型的小数精度可以达到53位。DOUBLE 与 REAL 同义。
DECIMAL(M,D) 非压缩的无符号浮点数。 在未压缩十进制中，每一位十进制数都对应一个字节。需要定义显示长度（M）和小数位数（D）。DECIMAL 与 NUMERIC 同义。
日期与时间类型
MySQL 包含以下几种日期与时间类型：

DATE YYYY-MM-DD （年-月-日）格式显示的日期，取值范围从1000-01-01 到 9999-12-31。比如1973年的12月30日就存为 1973-12-30。
DATETIME 按照 YYYY-MM-DD HH:MM:SS 格式组合显示的日期与时间，取值范围从1000-01-01 00:00:00 到 9999-12-31 23:59:59。比如说1973年的12月30日下午3 : 30就存为1973-12-30 15 : 30 : 00。
TIMESTAMP 介于1970年1月1日凌晨与2037年某个时间点之间的一种时间戳。这种格式与之前的 DATETIME 格式相仿，只不过少了数字间的连字符。1973年12月30日下午3 : 30被存为19731230153000（YYYYMMDDHHMMSS）。
TIME 按照 HH:MM:SS 格式存储的时间。
YEAR(M) 用2位或4位格式存储的时间。如果把长度定为2，比如说YEAR(2)，那么可以表示从1970年到2069年的这些年份（70-69）。如果把长度定为4，YEAR(4)，则可以表示从1901年到2155年。默认长度为4。
字符串类型
虽然数字与日期类型都很有趣，但通常我们存储最多的就是字符串了。下面列出了 MySQL 中常见的字符串类型。

CHAR(M) 长度固定的字符串，长度范围从1~255个字符，比如CHAR(5)。在存储时，会向右用空格补齐指定长度。长度并非必须参数，默认长度为1。
VARCHAR(M) 长度不定的字符串，长度范围从1~255个字符。比如：CHAR(25)。在创建VARCHAR字段时，必须定义长度。
BLOB or TEXT 最大长度为65535个字符的字段。BLOB是Binary Large Objects（二进制大型对象）的缩写，专用于保存大量的二进制数据，比如图片或其他类型的文件。TEXT 类型的文件也能保存大型数据。这两者的区别在于存储数据的排序和对比方面，BLOB类型数据是大小写敏感的，而TEXT类型数据则不是。另外，不能指定它们的长度。
TINYBLOB or TINYTEXT 最大长度为255个字符的 BLOB 或 TEXT 字段。同样也不能指定它们的长度。
MEDIUMBLOB or MEDIUMTEXT 最大长度为16777215个字符的 BLOB 或 TEXT 字段。同样也不能指定它们的长度。
LONGBLOB or LONGTEXT 最大长度为4294967295个字符的 BLOB 或 TEXT 字段。同样也不能指定它们的长度。
ENUM 枚举类型，是一种很独特的列表类型。ENUM 类型的数据实际是一个包含多个固定值的列表，只能选择这些值（包括 NULL 值）。例如，如果希望某个字段包含 "A"、"B" 和 "C"，必须这样定义：ENUM ('A', 'B', 'C')，只有这些值（或 NULL 值）能够填充到该字段中。




创建 MySQL 表
CREATE TABLE table_name (column_name column_type);
 
mysql> CREATE TABLE tutorials_tbl(
   -> tutorial_id INT NOT NULL AUTO_INCREMENT,
   -> tutorial_title VARCHAR(100) NOT NULL,
   -> tutorial_author VARCHAR(40) NOT NULL,
   -> submission_date DATE,
   -> PRIMARY KEY ( tutorial_id )
   -> );



在已有的数据库中创建新表，可以使用 PHP 的 mysql_query() 函数。利用正确的SQL命令为其传入第二个参数，就能创建出一张表。

以下范例展示如何利用 PHP 脚本来创建表。
<?php
    $sql = "CREATE TABLE tutorials_tbl( ".
           "tutorial_id INT NOT NULL AUTO_INCREMENT, ".
           "tutorial_title VARCHAR(100) NOT NULL, ".
           "tutorial_author VARCHAR(40) NOT NULL, ".
           "submission_date DATE, ".
           "PRIMARY KEY ( tutorial_id )); ";
    mysql_select_db( 'TUTORIALS' );
    $retval = mysql_query( $sql, $conn );
    if(! $retval )
    {
      die('Could not create table: ' . mysql_error());
    }
    echo "Table created successfully\n";


    $sql = "DROP TABLE tutorials_tbl";
    mysql_select_db( 'TUTORIALS' );
    $retval = mysql_query( $sql, $conn );
    if(! $retval )
    {
      die('Could not delete table: ' . mysql_error());
    }
    echo "Table deleted successfully\n";


    if(! get_magic_quotes_gpc() )
    {
    $tutorial_title = addslashes ($_POST['tutorial_title']);
    $tutorial_author = addslashes ($_POST['tutorial_author']);
    }
    else
    {
    $tutorial_title = $_POST['tutorial_title'];
    $tutorial_author = $_POST['tutorial_author'];
    }
    $submission_date = $_POST['submission_date'];

    $sql = "INSERT INTO tutorials_tbl ".
        "(tutorial_title,tutorial_author, submission_date) ".
          "VALUES ".
        "('$tutorial_title','$tutorial_author','$submission_date')";
    mysql_select_db('TUTORIALS');
    $retval = mysql_query( $sql, $conn );
    if(! $retval )
    {
        die('Could not enter data: ' . mysql_error());
    }
    echo "Entered data successfully\n";
    mysql_close($conn);
    }
        
    ?>

MySQL 选择查询

SQL 的 SELECT 命令用于从 MySQL 数据库中获取数据。可以在mysql> 提示符中使用这一命令，也可以利用 PHP 等脚本来完成。

语法格式
利用 SELECT 命令从 MySQL 表中获取数据的一般语法格式如下：

SELECT field1, field2,...fieldN table_name1, table_name2...  
[WHERE Clause]  
[OFFSET M ][LIMIT N]  
可以通过逗号分隔一个或多个表，利用 WHERE 子句包含进多种条件，但 WHERE 子句并不是 SELECT 命令的可选部分。
可以在一个 SELECT 命令中获取一个或多个字段。
可以用星型符号（*）代替字段。这时，SELECT 将返回所有字段。
可以使用 WHERE 子句指定任何条件。
在 SELECT 将要返回记录的位置处，使用 OFFSET 可以指定一个偏移。默认偏移为0。
可以使用 LIMIT 属性来限制返回记录的数量。
利用命令行方式获取数据
使用 SELECT 命令从表 tutorials_tbl 中获取数据。


<?php

$sql = 'SELECT tutorial_id, tutorial_title, 
               tutorial_author, submission_date
        FROM tutorials_tbl';

mysql_select_db('TUTORIALS');
$retval = mysql_query( $sql, $conn );
if(! $retval )
{
  die('Could not get data: ' . mysql_error());
}
while($row = mysql_fetch_array($retval, MYSQL_ASSOC))
{
    echo "Tutorial ID :{$row['tutorial_id']}  <br> ".
         "Title: {$row['tutorial_title']} <br> ".
         "Author: {$row['tutorial_author']} <br> ".
         "Submission Date : {$row['submission_date']} <br> ".
         "--------------------------------<br>";
} 
echo "Fetched data successfully\n";
mysql_close($conn);
?>
行的内容被赋给变量 $row，随后输出行内所包含的值。

注意：一定要记住，在把一个数组值直接插入到字符串中时，一定要加花括号（{与}）。
在上面的例子中，常量 MYSQL_ASSOC 被用作 PHP 函数 mysql_fetch_array() 的第二个参数，因此才会将行按照关联数组的形式返回。利用关联数组，我们可以使用字段的名字来访问字段，而不需要用到索引。

PHP 还提供了另一个叫做 mysql_fetch_assoc() 的函数，也会将行以关联数组的形式返回。

范例

在下面的范例中，利用 mysql_fetch_assoc() 函数来显示 tutorials_tbl 表中的所有记录。

<?php

$sql = 'SELECT tutorial_id, tutorial_title, 
               tutorial_author, submission_date
        FROM tutorials_tbl';

mysql_select_db('TUTORIALS');
$retval = mysql_query( $sql, $conn );
if(! $retval )
{
  die('Could not get data: ' . mysql_error());
}
while($row = mysql_fetch_assoc($retval))
{
    echo "Tutorial ID :{$row['tutorial_id']}  <br> ".
         "Title: {$row['tutorial_title']} <br> ".
         "Author: {$row['tutorial_author']} <br> ".
         "Submission Date : {$row['submission_date']} <br> ".
         "--------------------------------<br>";
} 
echo "Fetched data successfully\n";



$sql = 'SELECT tutorial_id, tutorial_title, 
               tutorial_author, submission_date
        FROM tutorials_tbl';

mysql_select_db('TUTORIALS');
$retval = mysql_query( $sql, $conn );
if(! $retval )
{
  die('Could not get data: ' . mysql_error());
}
while($row = mysql_fetch_array($retval, MYSQL_NUM))
{
    echo "Tutorial ID :{$row[0]}  <br> ".
         "Title: {$row[1]} <br> ".
         "Author: {$row[2]} <br> ".
         "Submission Date : {$row[3]} <br> ".
         "--------------------------------<br>";
}
echo "Fetched data successfully\n";
mysql_close($conn);
?>
以上三个范例都会产生同样的结果。

释放内存
在每个 SELECT 语句末尾释放游标内存是一个非常好的做法。使用 PHP 函数 mysql_free_result() 就可以实现这一点，如下例所示。

范例

<?php

$sql = 'SELECT tutorial_id, tutorial_title, 
               tutorial_author, submission_date
        FROM tutorials_tbl';

mysql_select_db('TUTORIALS');
$retval = mysql_query( $sql, $conn );
if(! $retval )
{
  die('Could not get data: ' . mysql_error());
}
while($row = mysql_fetch_array($retval, MYSQL_NUM))
{
    echo "Tutorial ID :{$row[0]}  <br> ".
         "Title: {$row[1]} <br> ".
         "Author: {$row[2]} <br> ".
         "Submission Date : {$row[3]} <br> ".
         "--------------------------------<br>";
}
mysql_free_result($retval);
echo "Fetched data successfully\n";
mysql_close($conn);
?>
在获取数据时，还可以用更复杂的SQL语句。步骤和上面介绍的一样。



MySQL Where Clause

前面介绍了利用 SELECT 命令从表中获取数据。利用一种叫做 WHERE 子句的条件子句可以过滤结果。使用 WHERE 子句可以制定选择规则，从表中选择我们所需的记录。

语法格式
利用 SELECT 命令与 WHERE 子句从表中获取数据的一般语法格式如下所示：

SELECT field1, field2,...fieldN table_name1, table_name2...
[WHERE condition1 [AND [OR]] condition2.....
可以使用逗号分隔一个或多个表，从而在使用 WHERE 子句时，包含多个条件。但是 WHERE 子句并非 SELECT 命令的一个可选部分。
可以在使用 WHERE 子句时指定任何条件。
可以通过 AND 或 OR 运算符来指定一个或多个条件。
WHERE 子句可以和 DELETE 或 UPDATE 命令一起使用，同样是用于指定条件。
WHERE 子句的运作方式就像编程语言中的 if 条件语句一样。它会将给定值与MySQL表中的字段值进行对比，如果两值相等，则返回MySQL表中字段值的所在的行。

下面这张表列出了 WHERE 子句中使用的一些运算符。

假设字段 A 保存 10 这个值，字段 B 保存着 20，那么：

运算符	说明	范例
=	检查两个操作数是否相等。如果相等，则条件为真。	(A = B)非真
!=	检查两个操作数是否相等。如果不相等，则条件为真。	(A != B)为真
>	检查左侧操作数是否大于右边的操作数。如果大于，则条件为真。	(A > B)非真
<	检查左侧操作数是否小于右侧操作数。如果小于，则条件为真。	(A < B)为真
>=	检查左侧操作数是否大于或等于右侧操作数。如果是，则条件为真。	(A >= B)非真
<=	检查左侧操作数是否小于或等于右侧操作数。如果是，则条件为真	(A <= B)为真
WHERE 子句可以非常方便地获取表中选定的行，尤其与 MySQL 的 Join 一起使用时。Join 稍后将择章另述。

另外，为了加快搜索，往往使用主键搜索记录。

如果表中记录并不匹配给定条件，查询不会返回任何数据。

采用命令行方式获取数据
使用 SQL 的 SELECT 命令与 WHERE 子句获取 表 tutorials_tbl 中的选定数据。


mysql> SELECT * from tutorials_tbl \
          WHERE BINARY tutorial_author='sanjay';

<?php
$sql = 'SELECT tutorial_id, tutorial_title, 
               tutorial_author, submission_date
        FROM tutorials_tbl
        WHERE tutorial_author="Sanjay"';

mysql_select_db('TUTORIALS');
$retval = mysql_query( $sql, $conn );
if(! $retval )
{
  die('Could not get data: ' . mysql_error());
}
while($row = mysql_fetch_array($retval, MYSQL_ASSOC))
{
    echo "Tutorial ID :{$row['tutorial_id']}  <br> ".
         "Title: {$row['tutorial_title']} <br> ".
         "Author: {$row['tutorial_author']} <br> ".
         "Submission Date : {$row['submission_date']} <br> ".
         "--------------------------------<br>";
} 
echo "Fetched data successfully\n";


$sql = 'UPDATE tutorials_tbl
        SET tutorial_title="Learning JAVA"
        WHERE tutorial_id=3';

mysql_select_db('TUTORIALS');
$retval = mysql_query( $sql, $conn );
if(! $retval )
{
  die('Could not update data: ' . mysql_error());
}
echo "Updated data successfully\n";


$sql = 'DELETE FROM tutorials_tbl
        WHERE tutorial_id=3';

mysql_select_db('TUTORIALS');
$retval = mysql_query( $sql, $conn );
if(! $retval )
{
  die('Could not delete data: ' . mysql_error());
}
echo "Deleted data successfully\n";
mysql_close($conn);
?>




前面几节讲解了如何利用 SQL 的 SELECT 命令来获取 MySQL 表中的数据，以及如何利用 WHERE 子句这种条件子句来选择所需的记录。

当我们需要进行精确匹配时，可以在WHERE子句中加入等号（=），就像是 if tutorial_author = 'Sanjay' 这种 if 条件语句一样。但有时我们会想在所有的结果中过滤 tutorial_author 字段包含 "jay" 字符的结果。这时就应该利用 SQL 的 LIKE 子句搭配 WHERE 子句来解决。

如果 SQL 的 LIKE 子句带有 % 字符，则相当于 UNIX 中的元字符（*），在命令行中列出所有的文件或目录。

如果 LIKE 子句不带 % 字符，则就相当于 WHERE 子句中带有等号的情况。

语法格式
使用 SQL 的 SELECT 命令，并配合 LIKE 子句，从 MySQL 表中获取数据的一般语法格式如下所示：

SELECT field1, field2,...fieldN table_name1, table_name2...
WHERE field1 LIKE condition1 [AND [OR]] filed2 = 'somevalue'
可以使用 WHERE 子句来指定任何条件。
可以搭配使用 LIKE 子句与 WHERE 子句。
LIKE 子句可以代替WHERE子句中的等号。
当 LIKE 子句带有 百分号（%）时，会按照元字符搜索那样执行。
使用 AND 或 OR 运算符，可以指定一个或多个条件。
WHERE...LIKE 子句组合还可以搭配 DELETE 或UPDATE 这样的 SQL 命令一起使用。其中，WHERE…LIKE 子句组合的作用同样是指定条件。
在命令行中使用 LIKE 子句
我们将使用 SQL 的SELECT 命令搭配WHERE...LIKE 子句组合，从 MySQL 的表 tutorials_tbl 中获取选定数据。


<?php
$sql = 'SELECT tutorial_id, tutorial_title, 
               tutorial_author, submission_date
        FROM tutorials_tbl
        WHERE tutorial_author LIKE "%jay%"';

mysql_select_db('TUTORIALS');
$retval = mysql_query( $sql, $conn );
if(! $retval )
{
  die('Could not get data: ' . mysql_error());
}
while($row = mysql_fetch_array($retval, MYSQL_ASSOC))
{
    echo "Tutorial ID :{$row['tutorial_id']}  <br> ".
         "Title: {$row['tutorial_title']} <br> ".
         "Author: {$row['tutorial_author']} <br> ".
         "Submission Date : {$row['submission_date']} <br> ".
         "--------------------------------<br>";
} 
echo "Fetched data successfully\n";


$sql = 'SELECT tutorial_id, tutorial_title, 
               tutorial_author, submission_date
        FROM tutorials_tbl
        ORDER BY  tutorial_author DESC';

mysql_select_db('TUTORIALS');
$retval = mysql_query( $sql, $conn );
if(! $retval )
{
  die('Could not get data: ' . mysql_error());
}
while($row = mysql_fetch_array($retval, MYSQL_ASSOC))
{
    echo "Tutorial ID :{$row['tutorial_id']}  <br> ".
         "Title: {$row['tutorial_title']} <br> ".
         "Author: {$row['tutorial_author']} <br> ".
         "Submission Date : {$row['submission_date']} <br> ".
         "--------------------------------<br>";
} 
echo "Fetched data successfully\n";


$sql = 'SELECT a.tutorial_id, a.tutorial_author, b.tutorial_count
        FROM tutorials_tbl a, tcount_tbl b
        WHERE a.tutorial_author = b.tutorial_author';

mysql_select_db('TUTORIALS');
$retval = mysql_query( $sql, $conn );
if(! $retval )
{
  die('Could not get data: ' . mysql_error());
}
while($row = mysql_fetch_array($retval, MYSQL_ASSOC))
{
    echo "Author:{$row['tutorial_author']}  <br> ".
         "Count: {$row['tutorial_count']} <br> ".
         "Tutorial ID: {$row['tutorial_id']} <br> ".
         "--------------------------------<br>";
} 
echo "Fetched data successfully\n";
mysql_close($conn);
?>
MySQL的左联结（LEFT JOIN）
MySQL 的左联结（LEFT JOIN）与简单使用 JOIN 的效果不同。左联结侧重考虑左侧的表。

如果进行左联结，除了得到所有跟以上联结同样的匹配记录之外，还会得到左侧表中未曾匹配的记录，从而保证了（在该范例中）照顾到了每一位作者。

mysql> SELECT a.tutorial_id, a.tutorial_author, b.tutorial_count
    -> FROM tutorials_tbl a LEFT JOIN tcount_tbl b
    -> ON a.tutorial_author = b.tutorial_author;
+-------------+-----------------+----------------+
| tutorial_id | tutorial_author | tutorial_count |
+-------------+-----------------+----------------+
|           1 | John Poul       |              1 |
|           2 | Abdul S         |           NULL |
|           3 | Sanjay          |              1 |
+-------------+-----------------+----------------+
3 rows in set (0.02 sec)
必须多加练习，才能熟悉 JOIN。这是 MySQL/SQL 中的一个比较复杂的概念，必须经过一番真实的案例磨炼才能真正地掌握它。




MySQL NULL Values

前面已经介绍了如何利用 SQL 的 SELECT 命令配合 WHERE 子句来获取 MySQL 表中的数据，但假如尝试给出一个条件，将字段或列值与 NULL 比对，则会出现异常。

为了处理这种情况，MySQL 提供了三种运算符：

IS NULL：如果列值为 NULL，则该运算符返回 true。
IS NOT NULL：如果列值不为NULL，则该运算符返回 true。
<=>：该运算符用于两个值的对比，当两个值相等时（即使这两个值都为 NULL 时，这一点与 = 运算符不同）返回 true。
包含 NULL 的条件都是比较特殊的。不能在列中使用 = NULL 或 ! = NULL 来寻找 NULL 值。这样的比对通常都是失败的，因为不可能得知这样的比对是否为真。即使 NULL = NULL 失败。

要想确定列是否为 NULL，要使用 IS NULL 或 IS NOT NULL。

在命令行中使用 NULL 值
假设数据库 TUTORIALS 中包含一张叫做 tcount_tbl 的表，这张表包含两列 tutorial_author 与 tutorial_count，则 tutorial_count 中出现的 NULL 值代表该字段值未知。



在 PHP 脚本中处理 NULL 值
可以使用 if...else 条件语句来准备一个基于 NULL 值的查询。

下面这个例子获取外部的 tutorial_count，并将其与表中的值进行比对。

<?php
if( isset($tutorial_count ))
{
   $sql = 'SELECT tutorial_author, tutorial_count
           FROM  tcount_tbl
           WHERE tutorial_count = $tutorial_count';
}
else
{
   $sql = 'SELECT tutorial_author, tutorial_count
           FROM  tcount_tbl
           WHERE tutorial_count IS $tutorial_count';
}

mysql_select_db('TUTORIALS');
$retval = mysql_query( $sql, $conn );
if(! $retval )
{
  die('Could not get data: ' . mysql_error());
}
while($row = mysql_fetch_array($retval, MYSQL_ASSOC))
{
    echo "Author:{$row['tutorial_author']}  <br> ".
         "Count: {$row['tutorial_count']} <br> ".
         "--------------------------------<br>";
} 
echo "Fetched data successfully\n";
mysql_close($conn);
?>




<?php
$con = mysql_connect("localhost", "userid", "password");
if (!$con)
{
  die('Could not connect: ' . mysql_error());
}

$db_list = mysql_list_dbs($con);

while ($db = mysql_fetch_object($db_list))
{
  echo $db->Database . "<br />";
}
mysql_close($con);
?>
获取服务器元数据
利用下面5种命令可以获取数据库服务器上的各种关键信息。它们既适用于命令行，也适用于 PHP 或 PERL 脚本。

命令	描述
SELECT VERSION()	表明服务器版本的字符串
SELECT DATABASE()	当前数据库名称（如果没有则为空值）
SELECT USER()	当前用户名
SHOW STATUS	服务器状态指示器
SHOW VARIABLES	服务器配置变量




在PHP中，调用 mysql_affected_rows() 函数来查找查询所影响的行数。

$result_id = mysql_query ($query, $conn_id);
# report 0 rows if the query failed
$count = ($result_id ? mysql_affected_rows ($conn_id) : 0);
print ("$count rows were affected\n");



当查询生成了 AUTO_INCREMENT 值后，通过调用 mysql_insert_id() 函数来获取该值。

mysql_query ("INSERT INTO insect (name,date,origin)
VALUES('moth','2001-09-14','windowsill')", $conn_id);
$seq = mysql_insert_id ($conn_id);
对已有序列进行重新编号
假如从表中删除了许多记录，必须对记录再次排序。这时可以使用一个小技巧来解决，但要记住当表还连接着其他表时，一定要非常小心。





防止 SQL 注入式攻击

if (preg_match("/^\w{8,20}$/", $_GET['username'], $matches))
{
   $result = mysql_query("SELECT * FROM users 
                          WHERE username=$matches[0]");
}
 else 
{
   echo "username not accepted";
}

使用 PERL 或 PHP 这样的脚本语言，可以很巧妙地处理转义字符。MySQL 针对 PHP 的扩展也提供了 mysql_real_escape_string() ，可以将输入的字符转义为MySQL所特有的字符。

if (get_magic_quotes_gpc()) 
{
  $name = stripslashes($name);
}
$name = mysql_real_escape_string($name);
mysql_query("SELECT * FROM users WHERE name='{$name}'");
LIKE 窘境
为了解决 LIKE 困境，常用的转义机制必须将 用户所输入的 % 和 _ 字符转义为字面值。使用 addcslashes() 能为你指定一个转义字符范围。

$sub = addcslashes(mysql_real_escape_string("%something_"), "%_");
// $sub == \%something\_
mysql_query("SELECT * FROM messages WHERE subject LIKE '{$sub}%'");




