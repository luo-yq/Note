#!/bin/sh  
  
mysql_port=3306  
mysql_username="root"  
mysql_password=""  
  
function_start_mysql()  
{  
    printf "Starting MySQL...\n"  
    /bin/sh /usr/local/webserver/mysql/bin/mysqld_safe --defaults-file=/data1/mysql/${mysql_port}/my.cnf 2>&1 > /dev/null &  
}  
function_stop_mysql()  
{  
    printf "Stoping MySQL...\n"  
    /usr/local/webserver/mysql/bin/mysqladmin -u ${mysql_username} -p${mysql_password} -h 127.0.0.1 -S /tmp/mysql.sock shutdown  
}    
function_restart_mysql()  
{  
    printf "Restarting MySQL...\n"  
    function_stop_mysql  
    sleep 5  
    function_start_mysql  
}    
function_kill_mysql()  
{  
    kill -9 $(ps -ef | grep 'bin/mysqld_safe' | grep ${mysql_port} | awk '{printf $2}')  
    kill -9 $(ps -ef | grep 'libexec/mysqld' | grep ${mysql_port} | awk '{printf $2}')  
}    
if [ "$1" = "start" ]; then  
    function_start_mysql  
elif [ "$1" = "stop" ]; then  
    function_stop_mysql  
elif [ "$1" = "restart" ]; then  
    function_restart_mysql  
elif [ "$1" = "kill" ]; then  
    function_kill_mysql  
else  
    printf "Usage: /data1/mysql/${mysql_port}/mysql {star|stop|restart|kill}\n"  
fi  
