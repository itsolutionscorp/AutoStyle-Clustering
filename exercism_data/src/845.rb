def compute(s1, s2)
    s1.chars.zip(s2.chars).count {|x, y| x != y }
  end