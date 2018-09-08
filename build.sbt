name := "ScalaShowOff"

version := "0.1"

scalaVersion := "2.12.6"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.0"