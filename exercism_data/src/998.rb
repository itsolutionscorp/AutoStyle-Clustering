def compute(strand1, strand2)
    pairs = strand1.chars.zip(strand2.chars)
    pairs.count { |c1, c2| c1 != c2 }
  end