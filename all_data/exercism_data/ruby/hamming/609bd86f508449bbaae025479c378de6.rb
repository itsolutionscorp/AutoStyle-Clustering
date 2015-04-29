class Hamming
  def self.compute(dna1, dna2)
    dist = dna1.chars.zip(dna2.chars)
    dist.count{|i| i[0] != i[1] }
  end
end
