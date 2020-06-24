import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    val df = DataFrame("iris.csv")

    df.normalize()

    val (train, test) = df.testTrainSplit(target = "Species", trainRatio = 0.75f, stratify = true, randomState = 42L)

    val kNeighborsClassifier = KNeighborsClassifier(k = 3)

    kNeighborsClassifier.fit(train)

    val pred = kNeighborsClassifier.predict(test, targetClass = "Species")

    println(accuracy(test.map { it["Species"] as String }, pred))
}

fun accuracy(test: List<String>, pred: List<String>): Double {
    return (test zip pred).count { it.first == it.second }.toDouble() / test.size.toDouble()
}
