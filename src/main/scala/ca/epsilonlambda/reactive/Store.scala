package ca.epsilonlambda.reactive

import japgolly.scalajs.react._

abstract class Store[M](reducer: Reducer[M]) {
  def connect[P](propsToElement: P => ReactElement, modelToProps: M => P, dispatchToProps: (P, (AnyRef => Callback)) => P = null) : ReactComponentU[_,_,_,_]
  def applyMiddleware(args: Any*) = throw new NotImplementedError
  def dispatch(action: AnyRef)
}