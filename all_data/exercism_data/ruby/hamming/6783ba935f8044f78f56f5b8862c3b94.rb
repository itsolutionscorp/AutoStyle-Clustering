#
# The Hamming class is responsible for computing the Hamming distance
# (http://en.wikipedia.org/wiki/Hamming_distance) between two DNA strands.
#
class Hamming

  #
  # Return the Hamming distance between two DNA strands, each represented
  # as a string of nucleobases, e.g. 'GAGCCTACTAACGGGAT'.  If the strands
  # have different lengths, the comparison will only be up to the length
  # of the shorter strand.
  #
  def self.compute(strand1, strand2)
    hamming_distance = 0
    num_chars_to_compare = [strand1.length, strand2.length].min
    (0...num_chars_to_compare).each do |i|
      hamming_distance += 1 if strand1[i] != strand2[i]
    end
    hamming_distance
  end

  #
  # One-liner solution (thanks to http://exercism.io/submissions/7c02e094a9674e63a9ba8e5d06c1fa58).
  #
  def self.compute2(strand1, strand2)                   # 'ACGT', 'AG'
    strand1.chars.zip(strand2.chars)                    # [['A','A'], ['C','G'], ['G',nil], ['T',nil]]
      .take_while { |first, second| first && second }   # [['A','A'], ['C','G']]
      .count { |first, second| first != second }        # [['C', 'G']]
  end

end
