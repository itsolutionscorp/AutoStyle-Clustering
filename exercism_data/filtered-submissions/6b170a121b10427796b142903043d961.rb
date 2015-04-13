def compute(a, b)
    shortest = [a.length, b.length].min

    (0...shortest).count { |i| a[i] != b[i] }
  end