def compute(strand_one, strand_two)
    strand_one.chars.zip(strand_two.chars).count { |pair| pair[0] != pair[1] }
  end