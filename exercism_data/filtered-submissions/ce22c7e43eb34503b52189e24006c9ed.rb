def compute(a, b)
    [a, b].min.chars.each_with_index.count { |i| a[i] != b[i] }
  end