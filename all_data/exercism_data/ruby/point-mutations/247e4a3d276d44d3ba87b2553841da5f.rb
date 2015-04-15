class DNA
  def initialize(strand)
    @strand = strand.chars
  end

  def hamming_distance(other_strand)
    other_strand = other_strand.chars
    n = [@strand.size, other_strand.size].min
    n.times.count { |x| @strand[x] != other_strand[x] }
  end
end
