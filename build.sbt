name := "akka-speedtest"

resolvers ++= Seq(
 "Akka Repository" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" % "akka" % "2.0.3",
  "com.typesafe.akka" % "akka-actor" % "2.0.3"
)
