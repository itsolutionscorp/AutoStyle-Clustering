def compute(x,y)
    (0...[x.length, y.length].min).count{|i| x[i] != y[i]}
  end