def compute(a, b)
    return -1 unless a.length == b.length
    a.length.times.count{|i| a[i] != b[i]}
  end