class DNA
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other_strand)
    HammingDistance.calculate(*equalize_strands(strand, other_strand))
  end

  private

  def equalize_strands(*strands)
    min_length = strands.map(&:length).min
    strands.map { |strand| strand[0, min_length] }
  end

  def strand
    @strand
  end
end


# http://en.wikipedia.org/wiki/Hamming_distance
module HammingDistance
  def self.calculate(s1, s2)
    raise "String length mismatch!" unless s1.length == s2.length
    s1.chars.zip(s2.chars).count { |c1, c2| c1 != c2 }
  end
end
