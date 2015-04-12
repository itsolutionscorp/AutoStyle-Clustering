def compute(dna1, dna2)
    hamming_distance = 0
    (dna1.size).times { |x| hamming_distance += 1 unless dna1[x] == dna2[x] }
    return hamming_distance
  end