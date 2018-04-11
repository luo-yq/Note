

在 DBI 脚本中，根据执行查询的方式，通过 do() 或 execute() 返回受查询影响的行数。

# Method 1
# execute $query using do( )
my $count = $dbh->do ($query);
# report 0 rows if an error occurred
printf "%d rows were affected\n", (defined ($count) ? $count : 0);

# Method 2
# execute query using prepare( ) plus execute( )
my $sth = $dbh->prepare ($query);
my $count = $sth->execute ( );
printf "%d rows were affected\n", (defined ($count) ? $count : 0);


# 获取当前数据库中的所有表  
my @tables = $dbh->tables ( );
foreach $table (@tables ){
   print "Table Name $table\n";
}



使用 mysql_insertid 属性来获取查询所生成的 AUTO_INCREMENT 值。根据查询方式，该属性可通过数据库句柄或语句句柄来访问。下面这个范例是从数据库句柄来引用该属性的。

$dbh->do ("INSERT INTO insect (name,date,origin)
VALUES('moth','2001-09-14','windowsill')");
my $seq = $dbh->{mysql_insertid};

