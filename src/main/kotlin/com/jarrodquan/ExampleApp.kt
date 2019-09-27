package com.jarrodquan

import com.jarrodquan.common.AppConfirm
import com.jarrodquan.common.AppResource
import com.jarrodquan.common.StageStackHelper
import javafx.application.Application
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.layout.StackPane
import javafx.stage.Stage
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.system.exitProcess

class ExampleApp : Application() {
    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(ExampleApp::class.java)
    }

    override fun init() {

    }

    override fun start(stage: Stage?) {
        LOGGER.debug("Starting...")

        if (stage != null) {
            val root = FXMLLoader.load<StackPane>(AppResource.load("fxml/login.fxml"))
            val scene = Scene(root)

            stage.title = "登录"
            stage.icons.add(Image("http://www.javafxchina.net/main/logo.png"))
            stage.scene = scene
            stage.isResizable = false
            stage.onCloseRequest = EventHandler {
                AppConfirm(root)
                    .title("操作确认")
                    .content("确认退出系统？")
                    .show(
                        EventHandler {
                            Platform.exit()
                        },
                        null
                    )

                it.consume()
            }
            stage.show()
            StageStackHelper.STAGES.push(stage)
        } else {
            exitProcess(1)
        }
    }

    override fun stop() {

    }
}