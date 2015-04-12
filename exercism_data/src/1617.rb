def compute(x, y)
    raise 'Arguments must be of equal length.' if x.length != y.length
    # Each mismatched element increases the hamming distance.
    x.length.times.count {|n| x[n] != y[n]}
  end