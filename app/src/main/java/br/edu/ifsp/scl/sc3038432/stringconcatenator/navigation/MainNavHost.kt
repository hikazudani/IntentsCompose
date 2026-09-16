package br.edu.ifsp.scl.sc3038432.stringconcatenator.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.edu.ifsp.scl.sc3038432.stringconcatenator.ui.composable.AddWordScreen
import br.edu.ifsp.scl.sc3038432.stringconcatenator.ui.composable.HomeScreen

private const val NEW_WORD = "new_word"

@Composable
fun MainNavHost( // agrupa as rotas
    navHostController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) { backStackEntry ->
            val returnedWord = backStackEntry.savedStateHandle.get<String>(NEW_WORD)

            HomeScreen(
                returnedWord = returnedWord,
                modifier = modifier,
                onWordConsumed = { backStackEntry.savedStateHandle.remove<String>(NEW_WORD) },
                onAddWordClick = { currentString ->
                    navHostController.navigate(Screen.AddWordScreen.createRoute(currentString))
                }
            )
        }

        composable(
            route = Screen.AddWordScreen.route,
            arguments = listOf(
                navArgument(Screen.CURRENT_STRING) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            AddWordScreen(
                currentString = backStackEntry.arguments?.getString(Screen.CURRENT_STRING) ?: "",
                modifier = modifier,
                onConcatenateClick = { word ->
                    navHostController.previousBackStackEntry?.savedStateHandle?.set(NEW_WORD, word)
                    navHostController.popBackStack()
                }
            )
        }
    }
}