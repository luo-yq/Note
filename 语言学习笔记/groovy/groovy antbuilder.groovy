List jarPathList = []  
String d = "./lib/"  
new File(d).eachFile{  
    if(it.name.endsWith('jar'))  
        jarPathList << d + it.name  
}  
  
String libPath = jarPathList.join(';')  
String catalinaBase = 'tomcat-home-path'  
libPath += ";" + catalinaBase + 'lib/servlet-api.jar'  
  
def ant = new AntBuilder()  
  
ant.javac(srcdir:'./src',   
    classpath:libPath,   
    destdir:'./classes',  
    includes:'**/*.java',   
    fork:'true')  
ant.echo('Done')  