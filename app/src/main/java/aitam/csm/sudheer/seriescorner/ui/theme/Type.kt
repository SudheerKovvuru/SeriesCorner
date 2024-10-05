package aitam.csm.sudheer.seriescorner.ui.theme

import aitam.csm.sudheer.seriescorner.R
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


val Nunito = FontFamily(
    Font(R.font.nunito_regular)
)

val Poppins = FontFamily(
    Font(R.font.poppins_regular),
    Font(R.font.poppins_bold,FontWeight.Bold)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = Nunito,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 30.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Poppins,
        fontSize = 10.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )
)