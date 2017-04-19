name := "playtech"

version := "1.0"

scalaVersion := "2.11.8"


libraryDependencies ++= {
  Seq(
    "org.specs2" %% "specs2" % "2.4.2" % "test",
    "org.scalaz" % "scalaz-core_2.11" % "7.1.0"
  )

}