package ca.epsilonlambda.reactive.diode

import ca.epsilonlambda.reactive._
import japgolly.scalajs.react._


class DiodeStore[M](reducer: Reducer[M]) extends Store[M](reducer){
  val circuit = new ReducerCircuit(reducer)

  override def connect[P](propsToElement: P => ReactElement, modelToProps: M => P, dispatchToProps: (P, (AnyRef => Callback)) => P = null) : ReactComponentU[_,_,_,_] =
    circuit.connect(x=>x)((proxy) => propsToElement(modelToProps(proxy().model)))

  override def dispatch(action: AnyRef) = {
    circuit.dispatch(action)
  }
}