def compute(dna1, dna2)
    dna1.split('').zip(dna2.split('')).inject(0) do|sum, pair|
      pair[0] == pair[1] ? sum : sum + 1
    end
  end
end