def compute(a, b)
    (0...(a.length)).to_a.reject{|i| a[i] == b[i]}.size
  end