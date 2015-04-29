def compute(a, b)
    count = 0
    a.length.times { |i| count += 1 if a[i] != b[i] }
    count
  end