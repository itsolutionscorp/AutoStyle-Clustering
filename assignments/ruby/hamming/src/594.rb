def compute(a, b)
    [a, b].min.size.times.count { |i| a[i] != b[i] }
  end