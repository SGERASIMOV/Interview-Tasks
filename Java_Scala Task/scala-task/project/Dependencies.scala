import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  lazy val akka = "com.typesafe.akka" %% "akka-actor" % "2.5.11"
  lazy val akkaTest = "com.typesafe.akka" %% "akka-testkit" % "2.5.11"
  lazy val akkaQuartzScheduler = "com.enragedginger" %% "akka-quartz-scheduler" % "1.6.1-akka-2.5.x"
}
