def compute(strA, strB)

    seqA = strA.split('')
    seqB = strB.split('')


    individual_distances = seqA.zip(seqB).map{ |e| (e[0] != e[1]) ? 1 : 0 }

    individual_distances.inject(0) { |sum, i| sum + i }
  end