package ca.epsilonlambda.reactive

import japgolly.scalajs.react._

abstract class Store[M](reducer: Reducer[M]) {
  def connect[P](modelToProps: M => P, dispatchToProps: (P, (AnyRef => Callback)) => P, propsToElement: P => ReactElement) : ReactComponentU[_,_,_,_]
  def applyMiddleware(args: Any*) = throw new NotImplementedError
  def dispatch(action: AnyRef)
}