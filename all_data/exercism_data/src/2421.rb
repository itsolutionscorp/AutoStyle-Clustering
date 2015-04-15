def compute(strand1, strand2)
    strand1 = strand1.chars
    strand2 = strand2.chars
    strand1.zip(strand2).count { |a, b| a && b && a != b }
  end