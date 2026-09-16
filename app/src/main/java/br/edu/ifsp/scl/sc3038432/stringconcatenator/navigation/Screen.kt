package br.edu.ifsp.scl.sc3038432.stringconcatenator.navigation

import android.net.Uri

// selada restringe as classes que podem herdar, só terá as telas definidas aqui
sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen") // tela inicial

    //tela onde o usuário adiciona uma nova palavra
    object AddWordScreen : Screen("add_word_screen?$CURRENT_STRING={$CURRENT_STRING}") {
        fun createRoute(currentString: String) = //navega p/ tela
            "add_word_screen?$CURRENT_STRING=${Uri.encode(currentString)}" // formata rota para uri
    }
    companion object { // pertence à classe e está disponível globalmente
        const val CURRENT_STRING = "currentString"
    }
}