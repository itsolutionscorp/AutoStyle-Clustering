class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other)
    strand.chars.zip(other.chars).count do |(this, that)|
      this && that && this != that
    end
  end
end
