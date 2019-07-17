package com.jarrodquan.controller

import com.jfoenix.controls.*
import javafx.animation.FadeTransition
import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.input.MouseEvent
import javafx.scene.layout.StackPane
import javafx.util.Duration
import org.slf4j.LoggerFactory
import java.net.URL
import java.util.*

class LoginController : Initializable {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(LoginController::class.java)
    }

    @FXML
    internal lateinit var rootStackPane: StackPane
    @FXML
    internal lateinit var txtUsername: JFXTextField
    @FXML
    internal lateinit var txtPassword: JFXPasswordField
    @FXML
    internal lateinit var btnLogin: JFXButton
    @FXML
    internal lateinit var btnExit: JFXButton

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        LOGGER.debug("Initializing...")

        appendScaleAnimation(btnLogin, btnExit)
    }

    fun btnOnMouseClicked(event: MouseEvent) {
        val sourceButton = event.source as Button

        LOGGER.info("You has clicked \"${sourceButton.text}\" button.")
    }

    fun login(event: MouseEvent) {
        LOGGER.debug("Sending request for login...")
    }

    fun exit(event: MouseEvent) {
        val btnConfirm = JFXButton("确认")
        val btnCancel = JFXButton("取消")
        val jfxDialogLayout = JFXDialogLayout()

        jfxDialogLayout.setHeading(Label("操作确认"))
        jfxDialogLayout.setBody(Label("确认退出系统？"))
        jfxDialogLayout.setActions(btnConfirm, btnCancel)

        val dialog = JFXDialog(rootStackPane, jfxDialogLayout, JFXDialog.DialogTransition.BOTTOM, false)

        btnConfirm.onMouseClicked = EventHandler {
            Platform.exit()
        }
        btnCancel.onMouseClicked = EventHandler {
            dialog.close()
        }

        dialog.show()
    }

    private fun appendFadeAnimation(vararg labels: Label) = labels.forEach {
        val fadeTransition = FadeTransition(Duration.seconds(3.0), it)

        fadeTransition.fromValue = 0.0
        fadeTransition.toValue = 1.0
        fadeTransition.play()
    }

    private fun appendScaleAnimation(vararg buttons: Button) = buttons.forEach {
        it.onMouseEntered = EventHandler {
            val sourceButton = it.source as Button
            val keyFrame = KeyFrame(
                Duration.millis(200.0),
                KeyValue(sourceButton.scaleXProperty(), 1.5),
                KeyValue(sourceButton.scaleYProperty(), 1.5)
            )
            val timeline = Timeline(keyFrame)

            timeline.keyFrames.add(keyFrame)
            timeline.play()
        }
        it.onMouseExited = EventHandler {
            val sourceButton = it.source as Button
            val keyFrame = KeyFrame(
                Duration.millis(200.0),
                KeyValue(sourceButton.scaleXProperty(), 1.0),
                KeyValue(sourceButton.scaleYProperty(), 1.0)
            )
            val timeline = Timeline(keyFrame)

            timeline.keyFrames.add(keyFrame)
            timeline.play()
        }
    }
}