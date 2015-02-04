import sbt._
import sbt.Keys._


object Owl2Build extends Build {

  val logger = ConsoleLogger()

  val baseVersion = "0.1.0"

  lazy val core = project.settings(
    organization := "uk.co.turingatemyhamster",
    scalaVersion := "2.11.5",
    crossScalaVersions := Seq("2.11.5", "2.10.4"),
    scalacOptions ++= Seq("-deprecation", "-unchecked"),
    version := baseVersion,
    resolvers += Resolver.url(
      "bintray-scalajs-releases",
      url("http://dl.bintray.com/scala-js/scala-js-releases/"))(
        Resolver.ivyStylePatterns),
    resolvers += "bintray/non" at "http://dl.bintray.com/non/maven",
    resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo),
    resolvers += "spray repo" at "http://repo.spray.io",
    resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
    resolvers += "drdozer Bintray Repo" at "http://dl.bintray.com/content/drdozer/maven",
    publishMavenStyle := true,
    //repository in bintray := "maven",
    //bintrayOrganization in bintray := None,
    licenses +=("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0.html"))
  )

}

