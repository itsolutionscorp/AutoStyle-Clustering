def compute(strand, strand2)
    pairs = strand.chars.zip(strand2.chars).first(strand2.length)
    pairs.count {|x,y| x != y }
  end