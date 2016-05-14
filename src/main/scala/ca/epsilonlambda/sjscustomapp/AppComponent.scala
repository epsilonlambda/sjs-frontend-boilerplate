package ca.epsilonlambda.sjscustomapp
import japgolly.scalajs.react._;
import japgolly.scalajs.react.vdom.prefix_<^._;
import sjs.react.bootstrap._;

object AppComponent {
  case class Props(
      message: String,
      n: Int,
      dispatch: (AnyRef => Unit),
      anotherMessage: String = ""
  )
  
  class Backend($: BackendScope[Props, Unit]) {
    def onClickIncrease() = CallbackTo[Unit] {

      $.props.runNow().dispatch(actions.Increase(10));

    }
    
    def onNothing() = CallbackTo[Unit] {
      $.props.runNow().dispatch(actions.NoOp)
    }
    
    def onFancyBootstrapClick() = CallbackTo[Unit]{
      println("Hey")
      $.props.runNow().dispatch(actions.Increase(2));
    }
    
    def onReset() =CallbackTo[Unit]{
      
    }
    
    def onChange(t: ReactEventI) = CallbackTo[Unit] {
      val message = t.target.value;
      $.props.runNow().dispatch(actions.SetOtherMessage(message))
    }
    
    
    def render(p: Props) : ReactElement = {
      <.div(
        <.h3(p.message)
        ,<.button(
            ^.onClick --> onClickIncrease,
            "Increase"
         )
        ,<.button(
            ^.onClick --> onNothing,
            "Nothing"
         )
        ,Button()(<.span(^.onClick --> onFancyBootstrapClick(), "Bootstrap!"))
        ,Button()(<.span(^.onClick --> (CallbackTo[Unit]{ $.props.runNow().dispatch(actions.Reset) }), "Reset")) 
        ,Input(`type` = "text", addonAfter = <.span("Addon"), ref="txt", value = p.anotherMessage, onChange = onChange)()
        ,<.h1("Bootstrap Integration")
        ,<.h2("Consensus v0.1")
        ,ProgressBar(now = p.n, max = 1000)()
      )
    }
  }
  
  val component = ReactComponentB[Props]("AppComponent")
    .stateless
    .renderBackend[Backend]
    .build
    
  def apply(p: Props) =
    component(p)
}