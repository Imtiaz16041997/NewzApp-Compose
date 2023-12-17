package com.imtiaz.newzapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.imtiaz.newzapp.R

data class Page(val title:String,val description:String,@DrawableRes val image:Int)

val pages = listOf(

    Page(
        title = "Lorem ipsum page 1",
        description = "Lorem ipsum didkdkdk dididkdkd",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Lorem ipsum page 2",
        description = "Lorem ipsum didkdkdk dididkdkd",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Lorem ipsum page 3",
        description = "Lorem ipsum didkdkdk dididkdkd",
        image = R.drawable.onboarding3
    )

)
