def compute(dna1, dna2)
    dna1.chars.zip(dna2.chars).count { |s1, s2| s1 != s2 && s1 && s2 }
  end