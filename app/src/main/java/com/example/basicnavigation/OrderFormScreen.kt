package com.example.basicnavigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun OrderFormScreen(
    onNavigateToDetails: (String, Int, Double) -> Unit,
    navController: NavController? = null,
    modifier: Modifier = Modifier
) {
    var productName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Formulir Pesanan",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = productName,
            onValueChange = {
                productName = it
                errorMessage = null
            },
            label = { Text("Product Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = quantity,
            onValueChange = {
                quantity = it
                errorMessage = null
            },
            label = { Text("Quantity") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = price,
            onValueChange = {
                price = it
                errorMessage = null
            },
            label = { Text("Price per Unit") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedButton(
                onClick = {
                    productName = ""
                    quantity = ""
                    price = ""
                    errorMessage = null
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Clear")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    val qtyInt = quantity.toIntOrNull()
                    val priceDbl = price.toDoubleOrNull()

                    when {
                        productName.isBlank() -> {
                            errorMessage = "Nama produk tidak boleh kosong"
                        }
                        qtyInt == null || qtyInt <= 0 -> {
                            errorMessage = "Jumlah harus berupa angka bulat positif"
                        }
                        priceDbl == null || priceDbl <= 0.0 -> {
                            errorMessage = "Harga harus berupa angka positif"
                        }
                        else -> {
                            errorMessage = null
                            onNavigateToDetails(productName.trim(), qtyInt, priceDbl)
                        }
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Submit")
            }
        }

        if (navController != null) {
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Go Back")
            }
        }
    }
}
