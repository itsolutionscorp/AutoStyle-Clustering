def compute(strand1, strand2)
    strand1.chars.zip(strand2.chars).count { |x| x[0] != x[1] }
  end