def compute(dna1, dna2)
    dna1.each_char.zip(dna2.each_char).select{ |s1, s2| s1 != s2 }.length
  end