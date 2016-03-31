package tutorial.webapp
import japgolly.scalajs.react._;
import japgolly.scalajs.react.vdom.prefix_<^._;
object AppComponent {
  case class Props(
      message: String,
      dispatch: (AnyRef => Callback)
  )
  
  class Backend($: BackendScope[Props, Unit]) {
    def onClickIncrease(p: Props)() = {
      p.dispatch(actions.Increase(1))
    }
    
    def onNothing(p: Props)() = {
      p.dispatch(actions.NoOp)
    }
    
    
    def render(p: Props) : ReactElement = {
      <.div(
        <.h3(p.message)
        ,<.button(
            ^.onClick --> onClickIncrease(p),
            "Increase"
         )
        ,<.button(
            ^.onClick --> onNothing(p),
            "Nothing"
         )
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