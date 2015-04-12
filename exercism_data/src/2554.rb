def compute(a, b)
    (0..[a.size, b.size].min-1)
      .count { |i| a[i] != b[i] }
  end