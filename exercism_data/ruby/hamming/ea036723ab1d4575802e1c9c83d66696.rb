class Hamming
  def self.compute(dna1, dna2)
    dna1.chars.zip(dna2.chars).select{ | char1, char2 | char1 != char2 }.count
  end
end
