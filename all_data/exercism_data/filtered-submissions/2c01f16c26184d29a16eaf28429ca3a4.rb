def compute(dna1, dna2)

    dna1, dna2 = dna2, dna1 if dna1.size > dna2.size
    dna1.chars.zip(dna2.chars).count{|a,b| a != b}
  end