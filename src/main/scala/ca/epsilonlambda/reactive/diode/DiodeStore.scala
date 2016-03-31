package ca.epsilonlambda.reactive.diode

import ca.epsilonlambda.reactive._
import japgolly.scalajs.react._


class DiodeStore[M](reducer: Reducer[M]) extends Store[M](reducer){
  val circuit = new ReducerCircuit(reducer)
  
  override def connect[P](modelToProps: M => P, dispatchToProps: (P, (AnyRef => Callback)) => P, propsToElement: P => ReactElement) : ReactComponentU[_,_,_,_] =
    circuit.connect(x=>x)((proxy) => propsToElement(dispatchToProps(modelToProps(proxy().model), proxy.dispatch)))
}