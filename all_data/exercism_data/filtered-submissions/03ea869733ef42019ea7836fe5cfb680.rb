def compute(strand_a, strand_b)
    strand_a.chars.zip(strand_b.chars).count{ |a, b| !!b && a != b }
  end