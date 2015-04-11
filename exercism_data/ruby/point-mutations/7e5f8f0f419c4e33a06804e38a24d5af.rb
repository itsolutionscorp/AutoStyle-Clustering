class DNA

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other)
    nucleotides.take(other.length).zip(other.chars).count { |(a, b)| a != b }
  end

  private

  def nucleotides
    strand.chars
  end

  attr_reader :strand
end
