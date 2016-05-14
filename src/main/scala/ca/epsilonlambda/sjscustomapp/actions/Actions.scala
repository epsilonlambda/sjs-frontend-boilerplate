package ca.epsilonlambda.sjscustomapp.actions

case class Increase(amount: Int)
case class Decrease(amount: Int)
case object Reset
case object NoOp
case class SetOtherMessage(message: String)