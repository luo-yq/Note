
9.4.     GUI
9.4.1.  java方式
import javax.swing.JFrame 
var jf = new JFrame("Hello!") 
jf.setSize(800, 600) 
jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) 
jf.setVisible(true)
 

9.4.2.  scala方式
import swing._, swing.Swing._

object Swing1 extends SimpleGUIApplication { // scala2.7
  def top = new MainFrame { // 必须实现top方法
    title = "窗口1"
    preferredSize = (400, 300)
   
    val label = new Label("hello world cn中文")
    contents = new BorderPanel {
      layout(label) = BorderPanel.Position.Center
    }
  }
}