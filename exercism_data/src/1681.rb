def compute(a, b)
    length = [a.length, b.length].min
    (0...length).count { |i| a[i] != b[i] }
  end