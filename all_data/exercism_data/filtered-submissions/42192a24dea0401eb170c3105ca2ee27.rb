def compute(a,b)
     a.chars.zip(b.chars).count {|i,j| j && i!=j}
  end