def compute(a, b)
    mismatches = 0
    [a.length, b.length].min.times { |i| mismatches += 1 if a[i] != b[i] }
    mismatches
  end