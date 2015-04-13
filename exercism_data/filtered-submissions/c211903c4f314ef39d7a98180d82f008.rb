def compute(dna1, dna2)
    hamdist = 0
    if dna1.length <= dna2.length
      nuc_length = dna1.length
    else
      nuc_length = dna2.length
    end

    i = 0
    while i < nuc_length
      if dna1[i] == dna2[i]
        hamdist = hamdist
      else
        hamdist = hamdist + 1
      end
      i += 1
    end
    hamdist
  end