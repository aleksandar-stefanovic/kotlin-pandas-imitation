package classification.knn

import kotlin.math.pow
import kotlin.math.sqrt

class KNeighborsClassifier(
    val k: Int = 3,
    // TODO implement weights
    weights: (distance: Double) -> Double = Weights.UNIFORM,
    // TODO implement spatial structures
    algorithm: Algorithm = Algorithm.Auto,
    algorithmLeafSize: Int = 30,
    // TODO implement metric
    metric: (inst1: Map<String, Any>, inst2: Map<String, Any>) -> Double = Metrics.EUCLIDEAN_DISTANCE
) {

    lateinit var data: List<Map<String, Any>>

    fun fit(train: List<Map<String, Any>>) {
        this.data = train
    }

    fun predict(test: List<Map<String, Any>>, targetClass: String): List<String> {

        if (this::data.isInitialized.not()) {
            error("Model must be trained before predicting, using KNeighborsClassifier::fit.")
        }

        val testData = test.map { it.filterKeys { it != targetClass } }

        val predicted = testData.map { instance ->
            data.asSequence()
                .map { trainInstance ->
                    val distance = sqrt(instance.keys.map { key ->
                        ((trainInstance[key] as Double) - (instance[key] as Double)).pow(2)
                    }.sum())

                    trainInstance[targetClass] to distance
                }
                .sortedBy { it.second }
                .take(k)
                .groupBy {
                    it.first
                }
                .maxBy { it.value.size }
                ?.key!! as String
        }

        return predicted
    }

}