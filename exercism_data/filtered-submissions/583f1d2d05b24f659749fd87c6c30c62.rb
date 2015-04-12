class Hamming
  def compute(dna1, dna2)
    dna1.chars.zip(dna2.chars).count{ | base1, base2 | base1 != base2 }
  end
end
