package com.jarrodquan

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.layout.Pane
import javafx.stage.Stage
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.system.exitProcess

class ExampleApplication : Application() {
    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(ExampleApplication::class.java)
    }

    override fun init() {

    }

    override fun start(stage: Stage?) {
        LOGGER.debug("Starting...")

        if (stage != null) {
            val root = FXMLLoader.load<Pane>(javaClass.classLoader.getResource("fxml/login.fxml"))
            val scene = Scene(root)

            stage.title = "OpenJFX11 example"
            stage.icons.add(Image("http://www.javafxchina.net/main/logo.png"))
            stage.scene = scene
            stage.show()
        } else {
            exitProcess(1)
        }
    }

    override fun stop() {

    }
}