def compute(dna1, dna2)
    hamdist = 0
    nuc_length = [dna1.length, dna2.length].min
    i = 0
    while i < nuc_length
      if dna1[i] != dna2[i]
        hamdist += 1
      end
      i += 1
    end
    hamdist
  end