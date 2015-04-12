def compute(dna1, dna2)
    dna1.chars.zip(dna2.chars).inject(0) do |count, pair|
      (pair[0] != pair[1]) ? count + 1 : count
    end
  end