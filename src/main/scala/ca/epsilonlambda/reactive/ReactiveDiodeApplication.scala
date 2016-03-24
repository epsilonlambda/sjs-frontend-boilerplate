package ca.epsilonlambda.reactive

import diode._

class ReactiveDiodeApplication[M <: AnyRef](circuit: Circuit[M]) {
    def applyMiddleware(processor: ActionProcessor[M]) {
      circuit.addProcessor(processor);
    }
}

