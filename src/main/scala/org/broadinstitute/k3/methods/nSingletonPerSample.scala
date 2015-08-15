package org.broadinstitute.k3.methods

import org.broadinstitute.k3.variant._

import scala.collection.Map

object nSingletonPerSample {
  def apply(vds: VariantDataset): Map[Int, Int] = {
    val singletons = sSingletonVariants(vds)

    vds
      .mapValuesWithKeys((v,s,g) => if (g.isNonRef && singletons.contains(v)) 1 else 0)
      .foldBySample(0)(_ + _)
  }
}