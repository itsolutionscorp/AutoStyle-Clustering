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
    min_length = strands.min_by(&:length).length
    strands.map { |strand| strand[0, min_length] }
  end

  def strand
    @strand
  end
end


# http://en.wikipedia.org/wiki/Hamming_distance
module HammingDistance
  def self.calculate(string1, string2)
    raise "String length mismatch!" unless string1.length == string2.length
    string1.chars.zip(string2.chars).count { |c1, c2| c1 != c2 }
  end
end
