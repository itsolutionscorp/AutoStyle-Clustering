def compute(dna1, dna2)
    dna1.chars.zip(dna2.chars).count { |pair| pair[0] != pair[1] }
  end
end