class Hamming
  def self.compute(strand_a, strand_b)
    overlayed = strand_a.chars.zip(strand_b.chars)
    overlayed.count { |v1, v2| v1 != v2 }
  end
end
