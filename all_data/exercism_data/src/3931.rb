def compute(dna1, dna2)
    dna1.chars.zip(dna2.chars).select{ | gene1, gene2 | gene1 != gene2 }.count
  end