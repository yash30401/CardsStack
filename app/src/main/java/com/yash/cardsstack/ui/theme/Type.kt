package com.yash.cardsstack.ui.theme


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.yash.cardsstack.R

// Set of Material typography styles to start with
@RequiresApi(Build.VERSION_CODES.Q)
val fonts = listOf(
    Font(R.font.poppins_regular),
    Font(R.font.poppins_medium),
    Font(R.font.poppins_bold),
    Font(R.font.poppins_thin),
    Font(R.font.poppins_black),
    Font(R.font.poppins_extra_bold),
    Font(R.font.poppins_italic),
    Font(R.font.poppins_light)
)
@RequiresApi(Build.VERSION_CODES.Q)
val fontFamily = FontFamily(fonts)

@RequiresApi(Build.VERSION_CODES.Q)
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = fontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)