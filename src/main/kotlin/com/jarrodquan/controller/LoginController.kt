package com.jarrodquan.controller

import com.jarrodquan.common.AppConfirm
import com.jarrodquan.common.AppResource
import com.jarrodquan.common.StageStackHelper
import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXPasswordField
import com.jfoenix.controls.JFXTextField
import com.sun.javafx.stage.StageHelper
import javafx.animation.FadeTransition
import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.layout.*
import javafx.stage.Stage
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

        rootStackPane.background = Background(
            BackgroundImage(
                Image(AppResource.load("images/background1.jpg")?.openStream()),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
            )
        )

        appendScaleAnimation(btnLogin, btnExit)
    }

    fun login() {
        val username = txtUsername.text
        val password = txtPassword.text

        if (username == null || username.isBlank()) {
            AppConfirm(rootStackPane)
                .title("温馨提示")
                .content("请输入用户名")
                .show(null, null)
            return
        }

        if (password == null || password.isBlank()) {
            AppConfirm(rootStackPane)
                .title("温馨提示")
                .content("请输入密码")
                .show(null, null)
            return
        }

        val u = "jarrodquan"
        val p = "123456"

        if (u == username && p == password) {
            val stage = Stage()
            val root = FXMLLoader.load<StackPane>(AppResource.load("fxml/dashboard.fxml"))
            val scene = Scene(root)

            stage.title = "仪表盘"
            stage.icons.add(Image("http://www.javafxchina.net/main/logo.png"))
            stage.scene = scene
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
            StageStackHelper.STAGES.pop().close()
            stage.show()
            StageHelper.setPrimary(stage, true)
            StageStackHelper.STAGES.push(stage)
        } else {
            AppConfirm(rootStackPane)
                .title("温馨提示")
                .content("用户名和密码不匹配")
                .show(null, null)
        }
    }

    fun exit() {
        AppConfirm(rootStackPane)
            .title("操作确认")
            .content("确认退出系统？")
            .show(
                EventHandler {
                    Platform.exit()
                },
                null
            )
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