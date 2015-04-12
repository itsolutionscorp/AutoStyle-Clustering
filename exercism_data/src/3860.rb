# The module holds functions for hamming distance calculation.
module Hamming
    # The function to compute the hamming distance of two strings.
    def compute(dna1, dna2)
        short, long = [dna1, dna2].sort_by { |array| array.length }

        short.chars.zip(long.chars).count { |char1, char2| char1 != char2 }
    end
end
