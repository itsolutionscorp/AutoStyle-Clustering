def compute x, y
    x.chars.zip(y.chars).reduce(0) { |memo, (c1, c2)| memo + (c1 <=> c2).abs }
  end