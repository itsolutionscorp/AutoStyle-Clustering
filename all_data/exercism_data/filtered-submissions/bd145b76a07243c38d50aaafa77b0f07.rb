def compute(a,b)
    (0..([a.length, b.length].min - 1)).inject(0) { |sum, i| a[i] != b[i] ? sum + 1 : sum }
  end