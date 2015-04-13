def compute(a, b)
    (0..(a.length - 1)).count { |i| a[i] != b[i] }
  end