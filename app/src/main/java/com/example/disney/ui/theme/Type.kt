package com.example.disney.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.disney.R

val DisneyFont = FontFamily(
    Font(R.font.spicyrice_regular, FontWeight.Bold),
    Font(R.font.outfit_regular, FontWeight.Normal),
    Font(R.font.outfit_semibold, FontWeight.SemiBold)

)
// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = Typography().headlineLarge.copy(fontFamily = DisneyFont),
    headlineMedium = Typography().headlineMedium.copy(fontFamily = DisneyFont),
    headlineSmall = Typography().headlineSmall.copy(fontFamily = DisneyFont),
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