
package sjs.react.bootstrap

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js

object TabPane /* mixins: */ {

  case class State(animateOut: Boolean = false, animateIn: Boolean = false)

  class Backend(t: BackendScope[Props, State]) {
    def startAnimateIn() {
      if (t.isMounted()) {
        t.setState(State(animateIn = false))
      }
    }

    def stopAnimateOut() {
      if (t.isMounted()) {
        t.setState(State(animateOut = false))
       val props = t.props.runNow()

        if (props.onAnimateOutEnd != null) {
          props.onAnimateOutEnd()
        }
      }
    }
  }

  val TabPane = ReactComponentB[Props]("TabPane")
    .initialState(State())
    .backend(new Backend(_))
    .renderPCS((scope, P, C, S) => {
      val B = scope.backend
    val classes = Map("tab-pane" -> true, "fade" -> true, "active" -> (P.active || S.animateOut), "in" -> (P.active && !S.animateIn))
    <.div(^.classSet1M(P.className, classes), C)
  }
    ).componentWillReceiveProps(args => CallbackTo[Unit] {
      val props = args.currentProps
      val nextProps = args.nextProps
      val state = args.currentState
      
      if (props.animation) {
        if (!state.animateIn && nextProps.active && !props.active) {
          args.component.setState(State(animateIn = true))
        } else if (!state.animateOut && !nextProps.active && props.active) {
          args.component.setState(State(animateOut = true))
        }

      }
  })
    //    .componentDidUpdate(scope=>{
    //    TransitionEvents
    //    true
    //  })
    .build

  /*

  */
  /*
    def componentDidUpdate(
      function() {
        if (this.state.animateIn)
        {
          setTimeout(this.startAnimateIn, 0)
        }
        if (this.state.animateOut)
        {
          TransitionEvents.addEndEventListener(this.getDOMNode(), this.stopAnimateOut)
        }
      }
    )
  */
  case class Props(eventKey:Any, tab:String, active: Boolean = true, animation: Boolean = true, className: String = "", onAnimateOutEnd: () => Unit = null)

  def apply(eventKey:Any, tab:String, active: Boolean = true, animation: Boolean = true, className: String = "", onAnimateOutEnd: () => Unit = null, ref: js.UndefOr[String] = "", key: js.Any = {})(children: ReactNode*) = {
    TabPane.set(key, ref)(Props(eventKey=eventKey, tab=tab, active = active, animation = animation, className = className, onAnimateOutEnd = onAnimateOutEnd), children)
  }

}
