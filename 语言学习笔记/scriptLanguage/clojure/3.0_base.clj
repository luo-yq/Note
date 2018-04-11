
;注释用  ;comment
;正则表达式  #“pattern”
;list   '(items）or (list items) or (quote (items))
;set    #{items}
;vector/array [items]
;map {k-v-pairs}
;匿名函数 #(single-expression)  用1%,2%...表示参数

;变量不可修改

;常用函数
;first/last string/[] 取字符串/集合的首/尾字母
;str str1 str2 拼接字符串
;oper value1 value2 操作符表达式
;= 完全相等 10！=10.0
;== 值相等 10==10.0

;dorun
;doall
;doseq

;谓词：
;类型判断：class?  coll?  decimal?  delay?  float?   fn?   instance?   integer?   isa?    keyword?
;		list?   macro?   map?   number?   seq?   set?   string?  vector?
;比较：<,<=,=,>,>=,not.==,compare,distinct?,identical
;逻辑关系：and  or   not  true?   false?    nil?
;集合类：empty?   every？ some?  not-empty?   not-every?   not-any?
;数字判断：even?   neg?  odd?    pos?   zero?

;def ^:dynamic v1 全局变量绑定
;defn f1 [] () 全局函数绑定
;binding [v 3] 全局绑定 需要事先有全局绑定
;let [var value] 局部绑定
;count [] 计算集合元素个数
;reverse [] 反转
;def simple-map (:k1 :v2,:k2 :v2,:k3 :v3.....)
;map类型：hash-map,sorted-map,
;def simple-map (sorted-map :k1 :v2,:k2 :v2,:k3 :v3.....)



;定义方法
(defn sayhelloto [name]
   (println "Hello", name)
) 
;调用方法
(sayhelloto "world")
;let设置变量并传递给函数  let [ varname  value]  fun
(let [first-letter (first "sfafal")]
	(println first-letter)
)
(println (str "afaf" "afha"))
(println (* 10 20.0))
(println (== 10.0 10))
(def ^:dynamic last-letter 1)
(println last-letter)

(defn f3[]
	(binding [last-letter (last "sfafal")]
(println last-letter)))
(def v2 (sorted-map :k1 :v1,:k2 :v2,:k3 :v3))
(def v2 (hash-map :k1 :v1,:k2 :v2,:k3 :v3))

(f3)

