def compute(strand1, strand2)
    strand1.chars.zip(strand2.chars).select{|x, y| x != y}.length
  end