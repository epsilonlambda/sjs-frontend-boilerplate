
package sjs.react.bootstrap

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._

import scala.scalajs.js


object PageHeader /* mixins: */ {


  val component = ReactComponentB[Props]("PageHeader")
    .renderPC((_, P, C) => {
    <.div(^.cls := P.className, ^.cls := "page-header", <.h1(C))
  }
    )
    .build

  case class Props(className: String = "")

  def apply(className: String = "", ref: js.UndefOr[String] = "", key: js.Any = {})(children: ReactNode*) = {
    component.set(key, ref)(Props(className = className), children)
  }

}
