def compute(dna1, dna2)


    dna1.chars.zip(dna2.chars).count{|a,b| a != b && a && b}
  end