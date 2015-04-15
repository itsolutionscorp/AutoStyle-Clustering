class DNA
  attr :strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other)
    strand
      .chars
      .slice(0, other.size)
      .zip(other.chars)
      .count { |x, y| x != y }
  end
end
