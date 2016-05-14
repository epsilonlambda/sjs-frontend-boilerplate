package ca.epsilonlambda.sjscustomapp
import scala.scalajs.js.JSApp;
import org.scalajs.dom;
import dom.document;
import scala.scalajs.js.annotation.JSExport;
import japgolly.scalajs.react._;
import japgolly.scalajs.react.vdom.prefix_<^._;
import ca.epsilonlambda.reactive._;
import ca.epsilonlambda.reactive.diode._;

object TutorialApp extends JSApp {
  def main(): Unit = {
    val store : Store[RootModel] = new DiodeStore[RootModel](AppReducer)
   
    ReactDOM.render(
        store.connect[AppComponent.Props](
            AppComponent(_),
            m => AppComponent.Props(m.counter.toString(), m.counter, store.dispatch, m.otherMessage)), 
            
        document.body
        )
  }
}