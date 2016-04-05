lazy val consensus = (project in file(".")).
	enablePlugins(ScalaJSPlugin).settings(
		name := "Consensus",
		version := "0.1-SNAPSHOT",
		scalaVersion := "2.11.7",
		persistLauncher := true,
		libraryDependencies ++= Seq(
		        "org.scala-js" %%% "scalajs-dom" % "0.8.0",
			"com.github.japgolly.scalajs-react" %%% "core"        % "0.10.4",
			"com.github.japgolly.scalajs-react" %%% "extra"        % "0.10.4",
			"com.lihaoyi"                       %%% "upickle"      % "0.3.5",
			"me.chrons"                         %%% "diode"        % "0.5.0",
			"me.chrons"                         %%% "diode-react"        % "0.5.0"
		),

		jsDependencies ++= Seq(
			"org.webjars.npm" % "react"     % "0.14.3" / "react-with-addons.js" commonJSName "React"    minified "react-with-addons.min.js",
			"org.webjars.npm" % "react-dom" % "0.14.3" / "react-dom.js"         commonJSName "ReactDOM" minified "react-dom.min.js" dependsOn "react-with-addons.js"
		)).dependsOn(reactBootstrap)

lazy val reactBootstrap = (project in file("scalajs-react-extra/react-bootstrap")).
  enablePlugins(ScalaJSPlugin).settings(
  scalaVersion := "2.11.7",
  persistLauncher := false,
  persistLauncher in Test := false,
  libraryDependencies ++= Seq(
	  "org.scala-js" %%% "scalajs-dom" % "0.8.0",
	  "com.github.japgolly.scalajs-react" %%% "extra"        % "0.10.4"
	  ))


