def compute(strand_a, strand_b)
    strand_a[0, strand_b.size].chars.zip(strand_b.chars).count { |x,y| x != y }
  end