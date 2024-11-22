package com.example.pratikum6dan6.Navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pratikum6dan6.ui.Screen.MahasiswaFormView
import com.example.pratikum6dan6.ui.Screen.RencanaStudyView
import com.example.pratikum6dan6.ui.Screen.SplashView
import com.example.pratikum6dan6.ui.Screen.TampilHasilView
import com.example.pratikum6dan6.ui.viewmodel.MahasiswaViewModel
import com.example.pratikum6dan6.ui.viewmodel.RencanaStudyViewModel


enum class Halaman{
    Splash,
    Mahasiswa,
    MataKuliah,
    Tampil
}

@Composable
fun MahasiswaApp(
    modifier: Modifier = Modifier,
    mahasiswaViewModel: MahasiswaViewModel = viewModel(),
    krsViewModel: RencanaStudyViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val mahasiswaUiState = mahasiswaViewModel.mahasiswaUiState.collectAsState().value
    val krsUiState = krsViewModel.krsStateUi.collectAsState().value

    NavHost(
        navController = navController,
        startDestination = Halaman.Splash.name,
        modifier = Modifier.padding()
    ) {
        composable(route = Halaman.Splash.name) {
            SplashView(onMulaiButton = {
                navController.navigate(Halaman.Mahasiswa.name)
            }
            )
        }
        composable(route = Halaman.Mahasiswa.name) {
            MahasiswaFormView(
                onSubmitButtonClicked = {
                    mahasiswaViewModel.saveDataMahasiswa(it)
                    navController.navigate(Halaman.MataKuliah.name)
                },
                onBackButtonClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}
