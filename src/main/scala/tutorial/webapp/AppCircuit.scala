package tutorial.webapp

import diode._
import diode.react._
import diode.ActionResult.ModelUpdate

// Define the root of our application model
case class RootModel(counter: Int)

// Define actions
case class Increase(amount: Int)

case class Decrease(amount: Int)

case object Reset
case object NoOp
/**
  * AppCircuit provides the actual instance of the `RootModel` and all the action
  * handlers we need. Everything else comes from the `Circuit`
  */
object AppCircuit extends Circuit[RootModel] with ReactConnector[RootModel] {
  // define initial value for the application model
  def initialModel = RootModel(111)

  // zoom into the model, providing access only to the
  val counterHandler = new ActionHandler(zoomRW(_.counter)((m, v) => m.copy(counter = v))) {
    def doHandle: PartialFunction[AnyRef, ActionResult[RootModel]] = {
        case Increase(a) => updated(value + a)
        case Decrease(a) => updated(value - a)
        case Reset => updated(0)
        case NoOp => noChange
    };
    
    
    override def handle = doHandle
  }
  

  val actionHandler = combineHandlers(counterHandler)
  /*
    // without the ActionHandler class, we would define the handler like this
    val actionHandler: PartialFunction[AnyRef, ActionResult[RootModel]] = {
      case Increase(a) => ModelUpdate(model.copy(counter = model.counter + a))
      case Decrease(a) => ModelUpdate(model.copy(counter = model.counter - a))
      case Reset => ModelUpdate(model.copy(counter = 0))
    }
  */
}

