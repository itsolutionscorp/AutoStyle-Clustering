def compute(strand1, strand2)
    strands = strand1.chars.zip(strand2.chars)
    strands.inject(0) do |total, nucleotides|
      n1, n2 = nucleotides
      total + (n1 && n2 && n1 != n2 ? 1 : 0)
    end
  end