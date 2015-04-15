class Hamming
  def self.compute (dna_1, dna_2)
    # get minimum of two lengths
    shortest_length = shortest_dna_length(dna_1, dna_2)

    # set initial Hamming distance
    hamming_distance = 0

    # iterate through two strands and compare
    for dna_index in 0...shortest_length
      if bases_different? dna_1[dna_index], dna_2[dna_index]
        hamming_distance += 1
      end
    end

    # return distance
    hamming_distance
  end

  def self.bases_different? (base_1, base_2)
    base_1 != base_2
  end

  def self.shortest_dna_length (dna_1, dna_2)
    [dna_1.length, dna_2.length].min
  end
end
