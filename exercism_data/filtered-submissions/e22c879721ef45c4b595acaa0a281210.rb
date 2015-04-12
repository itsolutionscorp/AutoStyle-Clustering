class Hamming
  def compute(dna1, dna2)
    dna1_codes = dna1.codepoints
    dna2_codes = dna2.codepoints
    differences = 0
    while nucleotide1 = dna1_codes.shift() and nucleotide2 = dna2_codes.shift()
      differences += 1 if nucleotide1 != nucleotide2
    end
    differences
  end
end
