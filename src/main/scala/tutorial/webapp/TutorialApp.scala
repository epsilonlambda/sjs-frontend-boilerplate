package tutorial.webapp
import scala.scalajs.js.JSApp;
import org.scalajs.dom;
import dom.document;
import scala.scalajs.js.annotation.JSExport;
import japgolly.scalajs.react.ReactDOM;
import japgolly.scalajs.react.vdom.prefix_<^._;
import diode._;
import diode.react.ReactConnector;
import ca.epsilonlambda.reactive.DiodeLogger;

object TutorialApp extends JSApp {
  def main(): Unit = {
    AppCircuit.addProcessor(new DiodeLogger[RootModel]())  
    ReactDOM.render(
        AppCircuit.connect(x=>x)(proxy => 
          AppComponent(AppComponent.Props("Counter = " + proxy().counter, proxy.dispatch))),
        document.body
        
        )
  }
}