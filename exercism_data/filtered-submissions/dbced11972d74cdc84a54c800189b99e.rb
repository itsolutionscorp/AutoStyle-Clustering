def compute(strand_a, strand_b)
    [strand_a, strand_b].map(&:chars).transpose.count { |nuc| nuc.uniq.length > 1 }
  end