package com.david.voicememos.macaw.ui.composebase

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.sp
import com.david.voicememos.macaw.R

val RFlex = fontFamily(
    font(R.font.rflex_bold, FontWeight.Bold),
    font(R.font.rflex_regular, FontWeight.Normal),
    font(R.font.rflex_bold_italic, FontWeight.Bold, FontStyle.Italic),
    font(R.font.rflex_regular_italic, FontWeight.Normal, FontStyle.Italic)
)

// Set of Material typography styles to start with
val typography = Typography(
    body1 = TextStyle(
        fontFamily = RFlex,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h2 = TextStyle(
        fontFamily = RFlex,
        fontWeight = FontWeight.Bold,
        fontSize = 60.sp
    ),
    h5 = TextStyle(
        fontFamily = RFlex,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontFamily = RFlex,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    button = TextStyle(
        fontFamily = RFlex,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp
    ),
    /* Other default text styles to override

caption = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
)
*/
)