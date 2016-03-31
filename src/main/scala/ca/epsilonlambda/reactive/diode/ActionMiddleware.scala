package ca.epsilonlambda.reactive.diode

import diode._;

class ActionMiddleware[M] extends ActionProcessor[DiodeModel[M]] {
    override def process(dispatch: Dispatcher,
                           action: AnyRef,
                           next: (AnyRef) => ActionResult[DiodeModel[M]], 
                           currentModel: DiodeModel[M]): ActionResult[DiodeModel[M]] =
       next(new Action(currentModel.model, action))
}