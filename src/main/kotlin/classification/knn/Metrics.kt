package classification.knn

object Metrics {
    // TODO implement
    fun MINKOWSKI(p: Int): (inst1: Map<String, Any>, inst2: Map<String, Any>) -> Double {
        return {_, _ -> 0.0}
    }

    val MANHATTAN_DISTANCE = MINKOWSKI(1)
    val EUCLIDEAN_DISTANCE = MINKOWSKI(2)

}