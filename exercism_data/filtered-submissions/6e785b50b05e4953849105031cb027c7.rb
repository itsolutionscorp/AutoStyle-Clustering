def compute(strand_one, strand_two)
    strand_one.chars.zip(strand_two.chars).count { |nuc_a, nuc_b| nuc_a != nuc_b }
  end