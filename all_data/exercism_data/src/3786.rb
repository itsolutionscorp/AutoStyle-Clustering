def compute(dna1, dna2)
    diff = 0
    for i in (0...[dna1.length, dna2.length].min)
      diff += 1 if dna1[i] != dna2[i]
    end
    diff
  end