def compute(strand_1, strand_2)
      hamming_distance = 0

      strand_1.each_char.with_index do |nucleotide, index|
        hamming_distance += (nucleotide == strand_2[index] ? 0 : 1)
      end

      hamming_distance
    end