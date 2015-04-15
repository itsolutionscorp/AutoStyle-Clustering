(import collections)

(defn word-count [phrase]
  (collections.Counter (.split phrase))
 )
