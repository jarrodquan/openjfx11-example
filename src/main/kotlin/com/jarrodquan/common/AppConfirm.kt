package com.jarrodquan.common

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXDialog
import com.jfoenix.controls.JFXDialogLayout
import javafx.event.EventHandler
import javafx.scene.control.Label
import javafx.scene.input.MouseEvent
import javafx.scene.layout.StackPane

class AppConfirm(root: StackPane) {
    var root: StackPane = root
        private set
    var title: String = ""
        private set
    var content: String = ""
        private set

    fun title(title: String): AppConfirm {
        this.title = title

        return this
    }

    fun content(content: String): AppConfirm {
        this.content = content

        return this
    }

    fun show(confirmEventHandler: EventHandler<MouseEvent>?, cancelEventHandler: EventHandler<MouseEvent>?) {
        val btnConfirm = JFXButton("确认")
        val btnCancel = JFXButton("取消")
        val jfxDialogLayout = JFXDialogLayout()

        jfxDialogLayout.setHeading(Label(title))
        jfxDialogLayout.setBody(Label(content))
        jfxDialogLayout.setActions(btnConfirm, btnCancel)

        val dialog = JFXDialog(root, jfxDialogLayout, JFXDialog.DialogTransition.BOTTOM, false)

        btnConfirm.onMouseClicked = EventHandler {
            dialog.close()
            confirmEventHandler?.handle(it)
        }
        btnCancel.onMouseClicked = EventHandler {
            dialog.close()
            cancelEventHandler?.handle(it)
        }

        dialog.show()
    }
}