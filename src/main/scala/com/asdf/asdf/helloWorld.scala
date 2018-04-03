import org.lwjgl.Version
import org.lwjgl.glfw.Callbacks.glfwFreeCallbacks
import org.lwjgl.glfw.GLFW._
import org.lwjgl.glfw.{GLFWErrorCallback, GLFWKeyCallback, GLFWVidMode}
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11._
import org.lwjgl.system.MemoryUtil.NULL

package com.asdf.asdf

class helloWorld {
  private var window: Long = 0

  def run() {
    System.out.println("Hello LWJGL " + Version.getVersion() + "!")

    try {
      init()
      loop()

      // Free the window callbacks and destroy the window
      glfwFreeCallbacks(window)
      glfwFreeCallbacks(window)
    }
    finally {
      // Terminate GLFW and free the error callback
      glfwTerminate()
      glfwSetErrorCallback(null).free()
    }

  }

  private def init() {
    // Setup an error callback. The default implementation
    // will print the error message in System.err.
    GLFWErrorCallback.createPrint(System.err).set()

    // Initialize GLFW. Most GLFW functions will not work before doing this.
    if (!glfwInit())
      throw new IllegalStateException("Unable to initialize GLFW")

    // Configure our window
    glfwDefaultWindowHints() // optional, the current window hints are already the default
    glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE) // the window will stay hidden after creation
    glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE) // the window will be resizable

    val WIDTH: Int = 300
    val HEIGHT: Int = 300

    // Create the window
    window = glfwCreateWindow(WIDTH, HEIGHT, "It lives!", NULL, NULL)

    if (window == NULL)
      throw new RuntimeException("Failed to create the GLFW window!")

    // Setup a key callback. It will be called every time a key is pressed, repeated or released.
    var kb = new KeyboardHandler()
    glfwSetKeyCallback(window, kb)

    // Get the resolution of the primary monitor
    var vidmode: GLFWVidMode = glfwGetVideoMode(glfwGetPrimaryMonitor())
    // Center our window
    glfwSetWindowPos(
      window,
      (vidmode.width() - WIDTH) / 2,
      (vidmode.height() - HEIGHT) / 2)

    // Make the OpenGL context current
    glfwMakeContextCurrent(window)

    // Enable v-sync
    glfwSwapInterval(1)

    // Make the window visible
    glfwShowWindow(window)
  }

  def loop() {
    // This line is critical for LWJGL's interoperation with GLFW's
    // OpenGL context, or any context that is managed externally.
    // LWJGL detects the context that is current in the current thread,
    // creates the GLCapabilities instance and makes the OpenGL
    // bindings available for use.
    GL.createCapabilities()

    glClearColor(1.0f, 0.0f, 0.0f, 0.0f)

    while (!glfwWindowShouldClose(window)) {
      glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT) // clear the framebuffer

      glfwSwapBuffers(window) // swap the color buffers

      // Poll for window events. The key callback above will only be
      // invoked during this call.
      glfwPollEvents()
    }
  }
}

class KeyboardHandler extends GLFWKeyCallback {
  def invoke(window: Long, key: Int, scancode: Int, action: Int, mods: Int) {
    if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
      glfwSetWindowShouldClose(window, true) // We will detect this in our rendering loop
  }
}