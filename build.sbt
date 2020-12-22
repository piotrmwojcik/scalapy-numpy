scalaVersion := "2.13.2"

resolvers in ThisBuild += "jitpack" at "https://jitpack.io"

libraryDependencies += "com.github.shadaj.scalapy" %% "scalapy-core" % "90a9ea7b731d7f457d11659c4b945b2816dbd5c4"
libraryDependencies += "org.scalatest" %%% "scalatest" % "3.1.0" % Test
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.0" % Test

fork := true

import scala.sys.process._
javaOptions += s"-Djna.library.path=${"python3-config --prefix".!!.trim}/lib"

