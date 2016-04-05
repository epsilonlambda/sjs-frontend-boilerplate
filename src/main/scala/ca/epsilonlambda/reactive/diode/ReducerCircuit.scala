package ca.epsilonlambda.reactive.diode

import diode._
import diode.react._
import diode.ActionResult.ModelUpdate
import ca.epsilonlambda.reactive._
import scala.language.existentials

case class DiodeModel[M](model: M) extends UseValueEq
case class Action[M](prevState: M, action: AnyRef)

class ReducerCircuit[M](reducer: Reducer[M]) extends Circuit[DiodeModel[M]] with ReactConnector[DiodeModel[M]] {
  def initialModel = new DiodeModel(reducer.initialState)
  // TODO: Fix this 
  def noEq = new FastEq[Any]() { override def eqv(a: Any, b: Any) = false; }
  
  val handler = new ActionHandler(zoomRW(_.model)((m, t) => new DiodeModel(t))(noEq)) {
    def doHandle: PartialFunction[AnyRef, ActionResult[DiodeModel[M]]] = {
      
      case Action(prevState: M, action) => {
        updated(reducer(prevState, action))
      }
    }
 
    override def handle = doHandle
  }
  
  def actionHandler = combineHandlers(handler);
  addProcessor(new DiodeLogger[DiodeModel[M]])
  addProcessor(new ActionMiddleware[M])
}
