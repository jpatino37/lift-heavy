package dev.josepatino.liftheavy.ui

sealed class PlateSize(val lbs: Double) {
    object FortyFive : PlateSize(45.0)
    object ThirtyFive : PlateSize(35.0)
    object TwentyFive : PlateSize(25.0)
    object Fifteen : PlateSize(15.0)
    object Ten : PlateSize(10.0)
    object Five : PlateSize(5.0)
    object TwoPointFive : PlateSize(2.5)
}
