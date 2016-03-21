package tutorial.webapp
import scala.scalajs.js.JSApp;
import org.scalajs.dom;
import dom.document;
import scala.scalajs.js.annotation.JSExport;
import japgolly.scalajs.react.ReactDOM;
import japgolly.scalajs.react.vdom.prefix_<^._;
import diode._;
import diode.react.ReactConnector;

object TutorialApp extends JSApp {
  def main(): Unit = {
    AppCircuit.addProcessor(new diode.ActionProcessor[RootModel] {
      override def process(dispatch: Dispatcher, action: AnyRef, next: (AnyRef) => ActionResult[RootModel], currentModel: RootModel): ActionResult[RootModel] = {
        println("Original State " + currentModel.toString())
        val result = next(action)
        result match {
          case ActionResult.ModelUpdate(newModel) =>
            println("New state " + newModel.toString())
          case ActionResult.NoChange =>
            println("Model unchanged")
          case _ => 
            println("Side-effects only")
        }
        
        result
      }
    })  
    ReactDOM.render(
        AppCircuit.connect(x=>x)(proxy => 
          AppComponent(AppComponent.Props("Counter = " + proxy().counter, proxy.dispatch))),
        document.body
        
        )
  }
}