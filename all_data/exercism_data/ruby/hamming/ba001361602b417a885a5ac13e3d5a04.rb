module Hamming
  class << self
    def compute(strand_1, strand_2)
      hamming_distance = 0
      comparison_length = length_to_compare(strand_1, strand_2)

      for position in 0..comparison_length
        unless dna_match?(strand_1[position], strand_2[position])
          hamming_distance += 1
        end
      end

      hamming_distance
    end

    private
    def length_to_compare(strand_1, strand_2)
      [strand_1.length, strand_2.length].min - 1
    end

    def dna_match?(char_1, char_2)
      char_1 == char_2
    end
  end
end
