package ca.epsilonlambda.reactive

abstract class Reducer[M] {
  def initialState: M
  def apply(previousState: M, action: AnyRef) : M
}