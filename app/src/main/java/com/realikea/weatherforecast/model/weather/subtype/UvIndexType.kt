package com.realikea.weatherforecast.model.weather.subtype

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.realikea.weatherforecast.R

sealed class UvIndexType(
    @StringRes val uvIndexDesc: Int,
    @StringRes val recommendDesc: Int,
    @DrawableRes val colorIndex: Int
) {
    object Low : UvIndexType(
        uvIndexDesc = R.string.low,
        recommendDesc = (R.string.uv_low_recommend1 /*+
                R.string.uv_low_recommend2*/),
        colorIndex = R.drawable.low_uv
    )
    object Moderate : UvIndexType(
        uvIndexDesc = R.string.moderate,
        recommendDesc = (R.string.uv_moderate_recommend1 /*+
                R.string.uv_moderate_recommend2*/),
        colorIndex = R.drawable.moderate_uv
    )
    object High : UvIndexType(
        uvIndexDesc = R.string.high,
        recommendDesc = (R.string.uv_high_recommend1 /*+
                R.string.uv_high_recommend2*/),
        colorIndex = R.drawable.high_uv
    )
    object VeryHigh : UvIndexType(
        uvIndexDesc = R.string.very_high,
        recommendDesc = (R.string.uv_veryhigh_recommend1 /*+
                R.string.uv_veryhigh_recommend2*/),
        colorIndex = R.drawable.very_high_uv
    )
    object Extreme : UvIndexType(
        uvIndexDesc = R.string.extreme,
        recommendDesc = (R.string.uv_extreme_recommend1 /*+
                R.string.uv_extreme_recommend2*/),
        colorIndex = R.drawable.very_high_uv
    )
    object Unknown : UvIndexType(
        uvIndexDesc = R.string.null_text,
        recommendDesc = R.string.null_text,
        colorIndex = R.drawable.low_uv
    )

    companion object {
        fun fromWeatherWeb(uv: Double): UvIndexType {
            return when {
                uv <= 3 -> Low
                uv <= 5 -> Moderate
                uv <= 7 -> High
                uv <= 10 -> VeryHigh
                uv <= 12 -> Extreme
                else -> Unknown
            }
        }
    }
}