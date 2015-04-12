def compute(dna1, dna2)
    dna1.chars.zip(dna2.chars).select{|i| i[0] != i[1] }.count
  end