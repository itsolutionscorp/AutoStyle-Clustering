def compute(a,b)
    raise ArgumentError 'the strands must be of equal length' if a.length != b.length
    a.chars.zip(b.chars).count{|c1, c2|c1 != c2}
  end