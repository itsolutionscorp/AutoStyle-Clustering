class DNA
  attr_reader :strand
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other_strand)
    result = strand.chars.zip(other_strand.chars).select do |a, b|
      a && b && a != b
    end
    result.length
  end
end
