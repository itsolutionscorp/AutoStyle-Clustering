def compute(dna1, dna2)
    min_len=[dna1.size, dna2.size].min
    dna1.chars.zip(dna2.chars).take(min_len).count {|(a,b)| a != b }
  end