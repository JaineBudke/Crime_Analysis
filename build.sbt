name := """example-play-akka"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

EclipseKeys.preTasks := Seq(compile in Compile, compile in Test)
EclipseKeys.projectFlavor := EclipseProjectFlavor.Java
EclipseKeys.createSrc := EclipseCreateSrc.ValueSet(EclipseCreateSrc.ManagedClasses, EclipseCreateSrc.ManagedResources)
EclipseKeys.withSource := true
EclipseKeys.withJavadoc := true

scalaVersion := "2.12.8"

libraryDependencies += guice
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.6"
libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.5.18"
libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-core" % "3.6.0"
libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-mapping" % "3.6.0"
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.4"
libraryDependencies += "org.apache.kafka" % "kafka-streams" % "0.10.2.0"
