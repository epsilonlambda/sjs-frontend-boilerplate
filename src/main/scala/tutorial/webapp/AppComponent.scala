package tutorial.webapp
import japgolly.scalajs.react._;
import japgolly.scalajs.react.vdom.prefix_<^._;
import sjs.react.bootstrap._;

object AppComponent {
  case class Props(
      message: String,
      n: Int,
      dispatch: (AnyRef => Callback)
  )
  
  class Backend($: BackendScope[Props, Unit]) {
    def onClickIncrease() = {

      $.props.runNow().dispatch(actions.Increase(10));

    }
    
    def onNothing(p: Props)() = {
      p.dispatch(actions.NoOp)
    }
    
    def onFancyBootstrapClick() = {
      println("Hey")
      $.props.runNow().dispatch(actions.Increase(2));
    }
    
    def onReset() ={
      
    }
    
    
    def render(p: Props) : ReactElement = {
      <.div(
        <.h3(p.message)
        ,<.button(
            ^.onClick --> onClickIncrease(),
            "Increase"
         )
        ,<.button(
            ^.onClick --> onNothing(p),
            "Nothing"
         )
        ,Button()(<.span(^.onClick --> onFancyBootstrapClick(), "Bootstrap!"))
        ,Button()(<.span(^.onClick --> ({ $.props.runNow().dispatch(actions.Reset) }), "Reset")) 
        ,Input(`type` = "text", addonAfter = <.span("Addon"), ref="txt")()
        ,<.h1("Bootstrap Integration")
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