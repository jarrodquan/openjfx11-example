package com.jarrodquan.controller

import com.jarrodquan.common.StageStackHelper

/**
 * @author Jarrod Quan
 */
class DashboardController {
    fun enterFullScreen() {
        val primaryStage = StageStackHelper.STAGES.peek()
        primaryStage.isFullScreen = !primaryStage.isFullScreen
    }
}