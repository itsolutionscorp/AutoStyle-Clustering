def compute(x, y)

    return nil unless x.length == y.length

    x.length.times.count {|n| x[n] != y[n]}
  end