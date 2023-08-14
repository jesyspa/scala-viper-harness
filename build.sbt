ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

resolvers += "Local Maven Repository" at "file:///home/ru/.m2/repository"
libraryDependencies += "viper" % "silicon" % "1.1-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(
    name := "viper-debugging"
  )
