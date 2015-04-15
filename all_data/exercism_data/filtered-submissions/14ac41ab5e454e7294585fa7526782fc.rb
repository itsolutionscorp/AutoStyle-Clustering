def compute(strand1, strand2)
    strand1.chars.zip(strand2.chars).count {|base| base[0] != base[1]}
  end