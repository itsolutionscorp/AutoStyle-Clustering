def compute(a,b)
    raise ArgumentError 'the strands must be of equal length' if a.length != b.length
    (0..a.length).count{|i|a[i] != b[i]}
  end