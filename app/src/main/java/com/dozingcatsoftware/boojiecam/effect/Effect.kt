package com.dozingcatsoftware.boojiecam.effect

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.RectF
import android.util.Size
import com.dozingcatsoftware.boojiecam.CameraImage

/**
 * Created by brian on 12/16/17.
 */
data class EffectMetadata(val name: String, val parameters: Map<String, Any>) {
    fun toJson() = mapOf("name" to name, "params" to parameters)

    companion object {
        fun fromJson(json: Map<String, Any>?): EffectMetadata {
            if (json == null) {
                return EffectMetadata("", mapOf())
            }
            val params = json.getOrElse("params", {mapOf<String, Any>()}) as Map<String, Any>
            return EffectMetadata(json.getOrDefault("name", "") as String, params)
        }
    }
}

interface Effect {
    fun createBitmap(cameraImage: CameraImage): Bitmap

    fun createPaintFn(cameraImage: CameraImage): (RectF) -> Paint? {
        return {null}
    }

    fun effectName(): String

    fun effectParameters(): Map<String, Any> = mapOf()

    fun effectMetadata(): EffectMetadata = EffectMetadata(effectName(), effectParameters())
}