import diode._;
package ca.epsilonlambda.reactive {
  
  class DiodeLogger[M <: AnyRef] extends ActionProcessor[M] {
    
     override def process(dispatch: Dispatcher,
                           action: AnyRef,
                           next: (AnyRef) => ActionResult[M], 
                           currentModel: M): ActionResult[M] = {
       
          println("prev state " + currentModel.toString())
          println("action " + action.toString())
          val result = next(action)
          
          /* http://stackoverflow.com/a/7209881
           * Scala can't match multiple patterns with identifiers.
           */
          result match {
            case ActionResult.ModelUpdate(newModel) =>
              logModelUpdate(newModel)
            case ActionResult.ModelUpdateEffect(newModel, _) =>
              logModelUpdate(newModel)
            case ActionResult.NoChange =>
              println("Model unchanged")
            case _ => 
              println("Unsupported ActionResult")
          }
          
          result
    }
     
    def logModelUpdate(newModel: M) {
      println("next state " + newModel.toString())      
    }
    
  }
}