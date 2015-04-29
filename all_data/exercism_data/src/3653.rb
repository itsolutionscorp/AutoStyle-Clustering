def compute(strand_a, strand_b)
    [strand_a.chars, strand_b.chars].transpose.count { |a, b| a != b }
  end