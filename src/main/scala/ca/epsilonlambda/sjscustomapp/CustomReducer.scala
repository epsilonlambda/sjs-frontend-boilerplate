package ca.epsilonlambda.sjscustomapp

import ca.epsilonlambda.reactive._

// Define the root of our application model
case class RootModel(counter: Int, otherMessage: String)

object CustomReducer extends Reducer[RootModel] {
  override def initialState = RootModel(555, "INIT")
  
  override def apply(current: RootModel, action: AnyRef) =
    action match {
        case actions.Increase(a) => RootModel(current.counter + a, current.otherMessage)
        case actions.Decrease(a) => RootModel(current.counter - a, current.otherMessage)
        case actions.Reset => RootModel(0, current.otherMessage)
        case actions.SetOtherMessage(m) => RootModel(current.counter, m)
        case _ => current
    }
}