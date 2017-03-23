package com.tv.forecast.utils

import com.tv.forecast.R

class ImageResUtils {
    companion object {
        val weatherTypeIcon = listOf(
                R.drawable.ic_weather_type_0,
                R.drawable.ic_weather_type_1,
                R.drawable.ic_weather_type_2,
                R.drawable.ic_weather_type_3,
                R.drawable.ic_weather_type_4,
                R.drawable.ic_weather_type_5,
                R.drawable.ic_weather_type_6,
                R.drawable.ic_weather_type_7,
                R.drawable.ic_weather_type_8,
                R.drawable.ic_weather_type_9,
                R.drawable.ic_weather_type_10,
                R.drawable.ic_weather_type_11,
                R.drawable.ic_weather_type_12,
                R.drawable.ic_weather_type_13,
                R.drawable.ic_weather_type_14,
                R.drawable.ic_weather_type_15,
                R.drawable.ic_weather_type_16,
                R.drawable.ic_weather_type_17,
                R.drawable.ic_weather_type_18,
                R.drawable.ic_weather_type_19,
                R.drawable.ic_weather_type_20,
                R.drawable.ic_weather_type_21,
                R.drawable.ic_weather_type_22,
                R.drawable.ic_weather_type_23,
                R.drawable.ic_weather_type_24,
                R.drawable.ic_weather_type_25,
                R.drawable.ic_weather_type_26,
                R.drawable.ic_weather_type_27,
                R.drawable.ic_weather_type_28,
                R.drawable.ic_weather_type_29,
                R.drawable.ic_weather_type_30,
                R.drawable.ic_weather_type_31,
                R.drawable.ic_weather_type_32,
                R.drawable.ic_weather_type_33,
                R.drawable.ic_weather_type_34,
                R.drawable.ic_weather_type_35,
                R.drawable.ic_weather_type_36,
                R.drawable.ic_weather_type_37,
                R.drawable.ic_weather_type_38
        )
    }

    fun getImageResId(weatherType: Int): Int {
        when (weatherType) {
            in 0..38 -> return weatherTypeIcon[weatherType]
            else -> return R.drawable.ic_weather_type_99

        }
    }

    fun getBgImageResId(weatherCode: Int): Int{
        when (weatherCode) {
            0 -> return R.mipmap.bg_image_sunny_01
            2 ->  return R.mipmap.bg_image_sunny_02
            4 -> return R.mipmap.bg_image_cloudly_04
            in 5..6 -> return R.mipmap.bg_image_cloudly_56
            in 7..8 -> return R.mipmap.bg_image_cloudly_78
            9 -> return R.mipmap.bg_image_cloudly_09
            10 -> return R.mipmap.bg_image_rainly_10
            11 -> return R.mipmap.bg_image_flash_11
            in 13..15 ->return R.mipmap.bg_image_rain_131415
            in 20..23 -> return R.mipmap.bg_image_snow_20_23
            else -> return R.mipmap.default_bg_image
        }
    }
}