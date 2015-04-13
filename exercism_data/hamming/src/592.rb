def compute(strand1, strand2)
    pairs = strand1.each_char.zip(strand2.each_char)
    pairs.count { |c1, c2| c1 != c2 }
  end