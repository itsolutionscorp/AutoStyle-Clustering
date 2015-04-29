class DNA
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other_strand)
    string1, string2 = normalize_strand_lengths(strand, other_strand)
    HammingDistance.calculate(string1, string2)
  end

  private

  def normalize_strand_lengths(*strands)
    length = strands.min_by(&:length).length
    strands.map { |strand| strand[0, length] }
  end

  def strand
    @strand
  end
end


module HammingDistance
  # http://en.wikipedia.org/wiki/Hamming_distance
  def self.calculate(string1, string2)
    string1.chars.zip(string2.chars).count { |c1, c2| c1 != c2 }
  end
end
