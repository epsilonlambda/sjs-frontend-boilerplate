package tutorial.webapp

import ca.epsilonlambda.reactive._

// Define the root of our application model
case class RootModel(counter: Int)

object AppReducer extends Reducer[RootModel] {
  override def initialState = RootModel(555)
  
  override def apply(current: RootModel, action: AnyRef) =
    action match {
        case actions.Increase(a) => RootModel(current.counter + a)
        case actions.Decrease(a) => RootModel(current.counter - a)
        case actions.Reset => RootModel(0)
        case _ => current
    }
}