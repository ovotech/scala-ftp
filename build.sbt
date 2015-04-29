import sbt._

name := "scala-ftp"

organization := "uk.co.bbc"

version := "2.0-SNAPSHOT"

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
    "commons-net"   % "commons-net"    % "3.3"
  , "org.scalatest" % "scalatest_2.11" % "2.1.7" % "test"
)

resolvers ++= com.ovoenergy.sbt.Resolvers.all

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

lazy val root = (project in file(".")).
  settings(com.ovoenergy.sbt.Publish.defaultSettings: _*)

