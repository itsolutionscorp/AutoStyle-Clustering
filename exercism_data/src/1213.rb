def compute(dna1, dna2)
    mistakes = 0

    dna1 = dna1[0, dna2.length]
    dna2 = dna2[0, dna1.length]

    (0..dna1.length).each do |x|
      mistakes += 1 if dna1[x] != dna2[x]
    end

    mistakes
  end