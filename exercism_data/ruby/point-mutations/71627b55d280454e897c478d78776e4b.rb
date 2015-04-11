class DNA

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other)
    nucleotides.zip(other.chars).select { |(a, b)| a != b && !b.nil? }.count
  end

  private

  def nucleotides
    strand.chars
  end

  attr_reader :strand
end
