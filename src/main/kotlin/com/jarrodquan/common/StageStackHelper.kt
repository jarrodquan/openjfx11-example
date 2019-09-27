package com.jarrodquan.common

import javafx.stage.Stage
import java.util.*

class StageStackHelper private constructor() {
    companion object {
        val STAGES = Stack<Stage>()
    }
}