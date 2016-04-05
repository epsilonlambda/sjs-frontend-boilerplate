package sjs.utils.mixins

import japgolly.scalajs.react.ReactComponentB
import org.scalajs.dom
import org.scalajs.dom.Event
import sjs.utils.Events
import japgolly.scalajs.react.CallbackTo

import scala.scalajs.js

/**
  * Created by chandrasekharkode .
  */
trait WindowListeners {
  def listeners: List[(String,js.Function1[Event,_])]


}

object WindowListeners {
  def mixin[P,S,B,N <: japgolly.scalajs.react.TopNode] = (c:ReactComponentB[P,S,B,N]) => {
    c.componentDidMount(scope => CallbackTo[Unit] {
      val listeners = scope.backend.asInstanceOf[WindowListeners].listeners
      listeners.foreach{ case (name,function) => Events.on(dom.window,name,function) }
    })
      .componentWillUnmount(scope => CallbackTo[Unit] {
      val listeners = scope.backend.asInstanceOf[WindowListeners].listeners
      listeners.foreach{ case (name,function) => Events.off(dom.window,name,function) }
    })
  }
}
