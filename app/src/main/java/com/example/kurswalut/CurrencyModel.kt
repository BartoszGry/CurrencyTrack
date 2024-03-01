package com.example.kurswalut

data class CurrencyModel(
  val currency :String,
  val code:String,
  val mid:Double
)

data class CurrencyTable(
    val table: String,
    val no:String,
    val effectiveDate: String,
    val rates:List<CurrencyModel>){
}
