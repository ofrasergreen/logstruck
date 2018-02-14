lazy val logstruckSettings = Seq(
  organization := "com.logstruck",
  scalaVersion := "2.11.7",
  version := "0.6",

  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("Sonatype Snapshots Nexus" at nexus + "content/repositories/snapshots")
    else
      Some("Sonatype Releases Nexus"  at nexus + "service/local/staging/deploy/maven2")
  },
  credentials += Credentials(Path.userHome / ".ivy2" / ".credentials"),

  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "2.2.5" % "test",
    "org.scalacheck" %% "scalacheck" % "1.12.4" % "test",
    "org.scala-lang" % "scala-reflect" % scalaVersion.value
),

  licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
  homepage := Some(url("http://logstruck.com")),
  scmInfo := Some(ScmInfo(
    url("https://github.com/ofrasergreen/logstruck"),
    "scm:git@github.com:ofrasergreen/logstruck.git")),
  developers := List(
    Developer(id="ofrasergreen", name="Owen Fraser-Green", email="owen@fraser-green.com", url=url("https://www.fraser-green.com"))
  )
)

lazy val root = project.in(file("."))
  .aggregate(all.map(Project.projectToRef): _*)
  .settings(name := "logstruck")
  .settings(logstruckSettings: _*)

lazy val logger = project.in(file("logger"))
  .settings(name := "logger")
  .settings(moduleName := "logstruck-logger")
  .settings(logstruckSettings: _*)

lazy val example = project.in(file("example"))
  .dependsOn(logger, supportFile, supportJson, supportFluentd)
  .settings(name := "example")
  .settings(moduleName := "logstruck-example")
  .settings(logstruckSettings: _*)

def support(name: String) =
  Project(id = name, base = file(s"support/$name"))
    .dependsOn(logger)
    .settings(moduleName := "logstruck-" + name)
    .settings(logstruckSettings: _*)

lazy val supportJson = support("json")

lazy val supportFile = support("file")

lazy val supportLogstash = support("logstash")

lazy val supportFluentd = support("fluentd")

lazy val supportSlf4j = support("slf4j")
  .settings(libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.22")

lazy val all =
  Seq(logger, example, supportFile, supportJson, supportSlf4j, supportLogstash, supportFluentd)

