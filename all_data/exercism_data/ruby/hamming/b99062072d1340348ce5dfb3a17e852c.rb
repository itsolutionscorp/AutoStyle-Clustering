class Hamming
  def self.compute(strand1, strand2)
    pairs(strand1, strand2).count{|a, b| a != b}
  end

  def self.pairs(strand1, strand2)
    strand1.chars.zip(strand2.chars)
  end
end
