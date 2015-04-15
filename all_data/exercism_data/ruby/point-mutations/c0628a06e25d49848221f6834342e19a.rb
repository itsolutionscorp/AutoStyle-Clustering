class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other_strand)
    strand_chars.zip(other_strand.chars)
      .count { |a, b| b && a != b }
  end

  private
  def strand_chars
    @strand_chars ||= strand.chars
  end
end
