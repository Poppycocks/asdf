import sbt.Keys.libraryDependencies

val lwjglVersion = "3.1.6"
val lwjglNatives = "natives-windows"

lazy val commonSettings = Seq(
  organization := "com.asdf",
  scalaVersion := "2.12.4",
  version      := "0.1.0-SNAPSHOT"
)

lazy val root = (project in file("."))
  .settings(
    commonSettings,
    name         := "asdf",
    libraryDependencies ++= Seq(
      "org.lwjgl" % "lwjgl"         % lwjglVersion,
      "org.lwjgl" % "lwjgl-assimp"  % lwjglVersion,
      "org.lwjgl" % "lwjgl-bgfx"    % lwjglVersion,
      "org.lwjgl" % "lwjgl-glfw"    % lwjglVersion,
      "org.lwjgl" % "lwjgl-nanovg"  % lwjglVersion,
      "org.lwjgl" % "lwjgl-nuklear" % lwjglVersion,
      "org.lwjgl" % "lwjgl-openal"  % lwjglVersion,
      "org.lwjgl" % "lwjgl-opengl"  % lwjglVersion,
      "org.lwjgl" % "lwjgl-par"     % lwjglVersion,
      "org.lwjgl" % "lwjgl-stb"     % lwjglVersion,
      "org.lwjgl" % "lwjgl-vulkan"  % lwjglVersion,
      "org.lwjgl" % "lwjgl"         % lwjglVersion classifier lwjglNatives,
      "org.lwjgl" % "lwjgl-assimp"  % lwjglVersion classifier lwjglNatives,
      "org.lwjgl" % "lwjgl-bgfx"    % lwjglVersion classifier lwjglNatives,
      "org.lwjgl" % "lwjgl-glfw"    % lwjglVersion classifier lwjglNatives,
      "org.lwjgl" % "lwjgl-nanovg"  % lwjglVersion classifier lwjglNatives,
      "org.lwjgl" % "lwjgl-nuklear" % lwjglVersion classifier lwjglNatives,
      "org.lwjgl" % "lwjgl-openal"  % lwjglVersion classifier lwjglNatives,
      "org.lwjgl" % "lwjgl-opengl"  % lwjglVersion classifier lwjglNatives,
      "org.lwjgl" % "lwjgl-par"     % lwjglVersion classifier lwjglNatives,
      "org.lwjgl" % "lwjgl-stb"     % lwjglVersion classifier lwjglNatives
    )
  )