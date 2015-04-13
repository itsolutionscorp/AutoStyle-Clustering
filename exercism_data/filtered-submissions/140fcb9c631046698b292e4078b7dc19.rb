def compute(original_dna, mutated_dna)
    hamming_distance = 0
    step = 0
    original_dna.each_char do |c|

      unless c == mutated_dna[step]
        hamming_distance = hamming_distance + 1
      end
      step = step + 1

      if step > original_dna.length - 1 or step > mutated_dna.length - 1
        return hamming_distance
      end

    end
    return hamming_distance
  end