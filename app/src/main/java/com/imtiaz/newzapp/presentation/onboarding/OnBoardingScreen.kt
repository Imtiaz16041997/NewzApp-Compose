package com.imtiaz.newzapp.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.imtiaz.newzapp.presentation.Dimens.MediumPadding2
import com.imtiaz.newzapp.presentation.Dimens.PageIndicatorWidth
import com.imtiaz.newzapp.presentation.common.OnBoardingBackButton
import com.imtiaz.newzapp.presentation.common.OnBoardingNextButton
import com.imtiaz.newzapp.presentation.onboarding.components.OnBoardingPage
import com.imtiaz.newzapp.presentation.onboarding.components.PagerIndicator
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen() {

    Column(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(initialPage = 0)

        val buttonsState = remember {
            derivedStateOf {
                when (pagerState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }

        HorizontalPager(state = pagerState,pageCount = pages.size) { index ->
            OnBoardingPage(
                page =  pages[index]
            )

        }

        Spacer(modifier = Modifier.weight(1f))

        //Indicator
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            PagerIndicator(
                modifier = Modifier.width(PageIndicatorWidth),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage

        )

        //Button
        Row(verticalAlignment = Alignment.CenterVertically) {

            val scope = rememberCoroutineScope()
            //Hide the button when the first element of the list is empty
            if(buttonsState.value[0].isNotEmpty()){
                OnBoardingBackButton(
                    text = buttonsState.value[0],
                    onClick = {

                        scope.launch {
                            pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                        }
                    }
                )
            }

            OnBoardingNextButton(
                text = buttonsState.value[1],
                onClick = {
                    scope.launch {
                        if(pagerState.currentPage == 3){
                            //TODO: Navigate to Home Screen
                        }else{
                            pagerState.animateScrollToPage(
                                page = pagerState.currentPage + 1
                            )
                        }
                    }
                }
            )
        }

      }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}
