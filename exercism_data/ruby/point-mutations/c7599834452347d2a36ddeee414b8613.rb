class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other)
    length_to_compare = [strand.length, other.length].min
    (0...length_to_compare).inject(0) do |mutations, index|
      mutations + (strand[index] == other[index] ? 0 : 1)
    end
  end
end
