def compute(a, b)
    (0..[a.length,b.length].min-1).count {|i| a[i]!=b[i]}
  end