def compute(dna1, dna2)

    return unless dna1.length == dna2.length

    dna1.chars.zip(dna2.chars).count{|a, b| a != b}
  end