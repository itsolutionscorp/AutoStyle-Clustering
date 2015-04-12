def compute(x, y)
    x.chars.zip(y.chars).count{ |a, b| a != b }
  end