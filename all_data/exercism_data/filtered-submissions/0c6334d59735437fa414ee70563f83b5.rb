def compute(one, two)
    one.chars.zip(two.chars).count {|x, y| x != y }
  end