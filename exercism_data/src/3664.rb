def compute x, y
    x.chars.zip(y.chars).reduce(0) { |memo, tuple| memo += (tuple[0] <=> tuple[1]).abs }
  end