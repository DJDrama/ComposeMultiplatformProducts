package com.dj.kmmapp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.option.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
                App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
/*

fun generateImageLoader(): ImageLoader {
    return ImageLoader {
        options {
            androidContext(applicationContext)
        }
        components {
            setupDefaultComponents()
        }
        interceptor {
            // cache 25% memory bitmap
            bitmapMemoryCacheConfig {
                maxSizePercent(context, 0.25)
            }
            // cache 50 image
            imageMemoryCacheConfig {
                maxSize(50)
            }
            // cache 50 painter
            painterMemoryCacheConfig {
                maxSize(50)
            }
            diskCacheConfig {
                directory(context.cacheDir.resolve("image_cache").toOkioPath())
                maxSizeBytes(512L * 1024 * 1024) // 512MB
            }
        }
    }
}*/
