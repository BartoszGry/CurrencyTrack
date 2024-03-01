package com.example.kurswalut

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CurrencyScreen(modifier: Modifier = Modifier) {
    val currencyViewModel: MainViewModel = viewModel()
    val currencyState by currencyViewModel.currenciesState

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            currencyState.loading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }

            currencyState.error != null -> {
                Text(text = "${currencyState.error}")
            }

            else -> {
                CurrenciesList(currencies = currencyState.list)
            }
        }
        RefreshButton(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            onRefreshClick = { currencyViewModel.refresh() }
        )
    }
}


@Composable
fun CurrenciesList(currencies: List<CurrencyModel>) {
    LazyColumn {
        item {
            TableHeader()
        }
        items(currencies) { currencyModel ->
            CurrencyItem(currency = currencyModel)
        }
    }
}

@Composable
fun CurrencyItem(currency: CurrencyModel) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)
            .border(1.dp, Color.Gray),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Column(modifier = Modifier.weight(0.6f)) {
            Text(
                text = currency.currency,
                textAlign = TextAlign.Left,
                color = Color.Blue,
                fontSize = 20.sp
            )

        }
        Column(modifier = Modifier.weight(0.2f)) {
            Text(text = currency.code, color = Color.Red, fontSize = 20.sp)
        }
        Column(modifier = Modifier.weight(0.2f)) {
            Text(
                text = currency.mid.toString(),
                textAlign = TextAlign.Right,
                color = Color.Green,
                fontSize = 18.sp
            )
        }
    }

}

@Composable
fun TableHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .border(1.dp, Color.Gray)
            .background(Color.Black),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(0.6f)) {
            Text(
                text = "Currency", textAlign = TextAlign.Left,
                color = Color.Blue,
                style = MaterialTheme.typography.headlineMedium
            )

        }
        Column(modifier = Modifier.weight(0.2f)) {
            Text(
                text = "Code",
                color = Color.Red,
                style = MaterialTheme.typography.headlineMedium
            )
        }
        Column(modifier = Modifier.weight(0.2f)) {
            Text(
                text = "Mid", textAlign = TextAlign.Right,
                color = Color.Green,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }

}


@Composable
fun RefreshButton(
    modifier: Modifier = Modifier,
    onRefreshClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            modifier = Modifier,
            onClick = {
                onRefreshClick()
            },
        ) {
            Icon(
                Icons.Rounded.Refresh, contentDescription = "Refresh Icon",
            )
        }
    }
}