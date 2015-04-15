def compute(strand1, strand2)
    strand1.chars.zip(strand2.chars).count { |a, b| a != b }
  end