def compute(strand1, strand2)
    strand1.chars.take(strand2.length).zip(strand2.chars).count{|base1, base2| base1 != base2}
  end