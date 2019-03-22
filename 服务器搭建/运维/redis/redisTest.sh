#!/bin/sh


redis-cli

set tmp ss

get tmp


#批量事务处理，MULTI：开始，EXEC：结束，DISCARD：放弃
#1.开始事务。2.命令入队。3.执行事务。
#UNWATCH 取消 WATCH 命令对所有 key 的监视。
#WATCH key [key ...] 监视一个(或多个) key ，如果在事务执行之前这个(或这些) key 被其他命令所改动，那么事务将被打断,事务被打断后执行exec返回nil。
MULTI
SET book-name "Mastering C++ in 21 days"
GET book-name
SADD tag "C++" "Programming" "Mastering Series"
SMEMBERS tag
EXEC
 
#排序，分页，根据sort的内容匹配key查询数据
sort userids by user:*:age limit 0 2 get user:*:age

#查看配置信息
CONFIG GET *
#通过修改 redis.conf 文件或使用 CONFIG set 命令来修改配置。
CONFIG SET timeout 0
#dbfilename：指定本地数据库文件名，默认值为dump.rdb，requirepass：，masterauth：，unixsocket：，logfile：，pidfile：
#maxmemory：，maxmemory-samples：，timeout：当客户端闲置多长时间后关闭连接，如果指定为0，表示关闭该功能，tcp-keepalive：
#auto-aof-rewrite-percentage：，auto-aof-rewrite-min-size：，hash-max-ziplist-entries：，list-max-ziplist-entries：
#list-max-ziplist-value：，set-max-intset-entries：，zset-max-ziplist-entries：
#zset-max-ziplist-value：，hll-sparse-max-bytes：，lua-time-limit：，slowlog-log-slower-than：
#latency-monitor-threshold：，slowlog-max-len：，port：默认端口为6379，tcp-backlog：，
#databases：设置数据库的数量，默认数据库为0，可以使用SELECT <dbid>命令在连接上指定数据库id
#repl-ping-slave-period：，repl-timeout：，repl-backlog-size：，repl-backlog-ttl：
#maxclients：，watchdog-period：，slave-priority：，min-slaves-to-write：，min-slaves-max-lag：
#hz：，no-appendfsync-on-rewrite：，slave-serve-stale-data：，slave-read-only：，stop-writes-on-bgsave-error：
#daemonize：，rdbcompression：，rdbchecksum：，activerehashing：，repl-disable-tcp-nodelay：
#aof-rewrite-incremental-fsync：，appendonly：，dir：指定本地数据库存放目录，默认./，maxmemory-policy：
#appendfsync：，save：，
#loglevel：指定日志记录级别，Redis总共支持四个级别：debug、verbose、notice、warning，默认为verbose，client-output-buffer-limit：
#unixsocketperm：，slaveof：，notify-keyspace-events：，bind：默认绑定的主机地址127.0.0.1


