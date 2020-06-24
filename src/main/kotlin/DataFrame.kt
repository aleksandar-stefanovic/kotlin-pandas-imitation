import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class DataFrame(private val filepath: String) {

    val attributeList: List<String>
    val data: List<Map<String, Any>>

    init {

        val reader = File(filepath).bufferedReader()

        attributeList = reader.readLine().split(",")

        data = reader.lineSequence().map { line ->
            (attributeList zip line.split(",").map(this::discoverType)).toMap()
        }.toList()

    }

    operator fun get(vararg columns: String): List<Map<String, Any>> {
        return data.map { instance ->
            instance.filterKeys { it in columns }
        }
    }

    override fun toString(): String {
        return "$attributeList\n${data.joinToString("\n")}"
    }

    fun testTrainSplit(
        target: String,
        trainRatio: Float = 0.75f,
        stratify: Boolean = true,
        randomState: Long? = null
    ): Pair<ArrayList<Map<String, Any>>, ArrayList<Map<String, Any>>> {

        val trainSize = (data.size * trainRatio).toInt()

        val trainInstances = ArrayList<Map<String, Any>>(trainSize)
        val testInstances = ArrayList<Map<String, Any>>(data.size - trainSize)

        val shuffledData = if (randomState == null) data.shuffled() else data.shuffled(Random(randomState))
        shuffledData.forEachIndexed { index, instance ->
            if (index < trainSize) {
                trainInstances.add(instance)
            } else {
                testInstances.add(instance)
            }
        }

        return Pair(trainInstances, testInstances)
    }

    fun normalize() {
        // TODO
    }

    fun discoverType(raw: String) = raw.toDoubleOrNull() ?: raw

}