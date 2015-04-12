def compute(strand_a, strand_b)
    a, b = strand_a.chars, strand_b.chars

    # hamming distance
    (0..a.size).count { |i| a[i] != b[i] }
  end