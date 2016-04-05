package tutorial.webapp
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
        store.connect(
            m => new AppComponent.Props(m.counter.toString(), m.counter, (x: AnyRef) => CallbackTo(()=>Unit)), 
            (p: AppComponent.Props, dispatch) => new AppComponent.Props(p.message, p.n, dispatch), 
            (props: AppComponent.Props) => AppComponent(props)),
        document.body
        )
  }
}