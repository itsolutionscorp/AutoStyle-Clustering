def compute(a, b)
    [a, b].map(&:chars).transpose.count{ |x, y| x != y }
  end