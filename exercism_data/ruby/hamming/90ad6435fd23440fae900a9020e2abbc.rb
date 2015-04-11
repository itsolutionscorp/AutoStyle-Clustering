module Hamming

  def self.pairs(strand_1, strand_2)
    acid_pairs = strand_1.chars.zip(strand_2.chars)
    acid_pairs.delete_if { |acid_pair| acid_pair.include?(nil) }
  end

  def self.mismatches(strand_1, strand_2)
    self.pairs(strand_1, strand_2).select { |pair| pair[0] != pair[1] }
  end

  def self.compute(strand_1, strand_2)
    self.mismatches(strand_1, strand_2).length
  end

end
