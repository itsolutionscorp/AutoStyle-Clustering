# I set myself the challange of solving this without using
# a variable to count, and could think of only this solution.
#
# Alternatives???
#
class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other_strand)
    indexes_to_compare(strand, other_strand).select {|i|
      strand[i] != other_strand[i]
    }.size
  end

  def indexes_to_compare(strand, other_strand)
    (0..index_of_last_char_to_compare(strand, other_strand)).to_a
  end

  def index_of_last_char_to_compare(strand, other_strand)
    [strand.size, other_strand.size].min - 1
  end

end
