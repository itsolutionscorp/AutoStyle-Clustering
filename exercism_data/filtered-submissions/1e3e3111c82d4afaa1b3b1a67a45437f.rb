def compute(dna1, dna2)
    dna1.chars.zip(dna2.chars).count do |(a,b)| 
      a && b && a != b
    end
  end