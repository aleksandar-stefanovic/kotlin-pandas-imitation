# kotlin-pandas-imitation
A personal and subjective exploration into what Pandas could look like if it was created in Kotlin.

The project is currently in the exploration phase. The aim is to recreate some of the data mining algorithms present in Pandas and related libraries.

## Motivation

I, personally, like using statically-typed languages over dynamically-typed ones, and so, I've been frustrated when being forced to learn and use Pandas library in Python, and so I've fantasized about what would Pandas (and related libraries) look like it was written in a statically-typed, yet expressive language like Kotlin. Additionally, I believe that the API could be further enhanced, not just by enforcing types, but also by using a more object-oriented approach. Also, I'd like to further my understanding of data mining algorithms.

Even if it is nothing but a recreation of the original Pandas library, it would be exicting to be able to use this library everywhere, including mobile devices and the web, and so this project would strive to being completely indenpendent from any JVM-specific libraries (which is currently not the case, since there is some code that relies on the Java standard library).


## Planned features

Performance is currently not a priority, but ideally it would be improved in the future using Kotlin-specific concurrency tools, like coroutines.

### DataFrame-related
- [ ] Implement somewhat efficient tabular structure to hold data
- [ ] Recreate functions available for Pandas' DataFrame

### Algorithms
#### Classification
- [ ] Basic decision tree classifier, using something based off of Hunt's algorithm.
- [X] K-nearest neighbors classifier (initial version implemented, but lacks in features)
- [ ] Naïve Bayesian classifier

#### Cluster Analysis
- [ ] K-means clustering
- [ ] Agglomerative hierarchical clustering
- [ ] DBSCAN clustering

#### Association Rules
- [ ] Apriori algorithm

## Example

```kotlin
fun main() {
    val df = DataFrame("iris.csv")

    df.normalize()

    val (train, test) = df.testTrainSplit(target = "Species", trainRatio = 0.75f, stratify = true, randomState = 42L)

    val kNeighborsClassifier = KNeighborsClassifier(k = 3)

    kNeighborsClassifier.fit(train)

    val pred = kNeighborsClassifier.predict(test, targetClass = "Species")

    println(accuracy(test.map { it["Species"] as String }, pred))
}
```

`Output: 0.9736842105263158`
