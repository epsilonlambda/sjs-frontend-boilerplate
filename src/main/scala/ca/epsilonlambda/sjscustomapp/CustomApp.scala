package ca.epsilonlambda.sjscustomapp
import scala.scalajs.js.JSApp;
import org.scalajs.dom;
import dom.document;
import scala.scalajs.js.annotation.JSExport;
import japgolly.scalajs.react._;
import japgolly.scalajs.react.vdom.prefix_<^._;
import ca.epsilonlambda.reactive._;
import ca.epsilonlambda.reactive.diode._;

object CustomApp extends JSApp {
  def main(): Unit = {
    val store : Store[RootModel] = new DiodeStore[RootModel](CustomReducer)
   
    ReactDOM.render(
        store.connect[CustomComponent.Props](
            CustomComponent(_),
            m => CustomComponent.Props(m.counter.toString(), m.counter, store.dispatch, m.otherMessage)), 
            
        document.body
        )
  }
}