class Hamming
  def self.compute(dna1, dna2)
    dna1.chars.zip(dna2.chars).select{ | base1, base2 | base1 != base2 }.count
  end
end
