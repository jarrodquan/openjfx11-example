package com.jarrodquan.controller

import javafx.animation.FadeTransition
import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.input.MouseEvent
import javafx.util.Duration
import org.slf4j.LoggerFactory

class LoginController {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(LoginController::class.java)
    }

    @FXML
    private lateinit var lblHello: Label
    @FXML
    private lateinit var lblGoodbye: Label
    @FXML
    private lateinit var btnStart: Button
    @FXML
    private lateinit var btnOpen: Button
    @FXML
    private lateinit var btnEdit: Button

    fun init() {
        LOGGER.debug("Initializing...")

        appendFadeAnimation(lblHello, lblGoodbye)
        appendScaleAnimation(btnStart, btnOpen, btnEdit)
    }

    fun btnOnMouseClicked(event: MouseEvent) {
        val sourceButton = event.source as Button

        LOGGER.info("You has clicked \"${sourceButton.text}\" button.")
    }

    private fun appendFadeAnimation(vararg labels: Label) = labels.forEach {
        val fadeTransition = FadeTransition(Duration.seconds(2.0), it)

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