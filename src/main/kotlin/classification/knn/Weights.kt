package classification.knn


object Weights {
    val UNIFORM = { _: Double -> 1.0 }
    val DISTANCE = { distance: Double -> 1/distance }
}