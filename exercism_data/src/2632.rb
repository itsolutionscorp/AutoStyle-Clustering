def compute(a, b)
    max_length = [a.length, b.length].max

    max_length.times.count { |i| a[i] != b[i] }
  end