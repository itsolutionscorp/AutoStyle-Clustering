def compute(a, b)
    counter = 0
    a.length.times { |i| counter += 1 if a[i] != b[i] }
    counter
  end