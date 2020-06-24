package classification.knn

// TODO decide whether the name "Spatial Structure" would be more appropriate
sealed class Algorithm {
    // TODO define the interface of Algorithm
    object BallTree : Algorithm() // TODO implement
    object KdTree : Algorithm() // TODO implement
    object Brute : Algorithm() // TODO implement
    object Auto : Algorithm() // TODO implement

}