class DNA

  def initialize(strand)
    @strand = strand.chars
  end

  def hamming_distance(other_strand)
    @strand.zip(other_strand.chars).count do |a,b|
      a && b && a != b
    end
  end
end
