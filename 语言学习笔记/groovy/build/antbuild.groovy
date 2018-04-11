class Build {
   ant = new AntBuilder()
   base_dir = "path/to/my/DemoProject/"
   src_dir = base_dir + "src"
   lib_dir = base_dir + "lib"
   build_dir = base_dir + "classes"
   dist_dir = base_dir + "dist"
   file_name = "whoami"
   classpath = ant.path {
      fileset(dir: "${lib_dir}"){
         include(name: "*.jar")
      }
      pathelement(path: "${build_dir}")
   }
   clean() {
      ant.delete(dir: "${build_dir}")
      ant.delete(dir: "${dist_dir}")
   }
   build() {
      ant.mkdir(dir: "${build_dir}")
      ant.javac(destdir: "${build_dir}", srcdir: "${src_dir}", classpath: "${classpath}")       
   }
   jar() {
      clean()
      build()
      ant.mkdir(dir: "${dist_dir}")
      ant.jar(destfile: "${dist_dir}/${file_name}.jar", basedir: "${build_dir}")
   }
   static void main(args) {
      b = new Build()
      b.run(args)
   }
   void run(args) {
      if ( args.size() > 0 ) { 
         invokeMethod(args[0], null )
      }
      else {
         build()
      } 
   }
}