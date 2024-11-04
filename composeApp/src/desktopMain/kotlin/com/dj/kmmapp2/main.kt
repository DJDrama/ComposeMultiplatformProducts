package com.dj.kmmapp2

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KmpApp2",
    ) {
        App()
    }
}