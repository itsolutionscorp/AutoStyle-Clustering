def compute(x, y)
    return nil unless x.length == y.length
    hamming_distance = 0
    x.length.times {|n| hamming_distance += 1 if x[n] != y[n]}
    hamming_distance
  end