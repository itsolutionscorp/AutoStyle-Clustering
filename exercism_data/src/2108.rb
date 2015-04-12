def compute(first, second)
    first.chars.zip(second.chars).select{|p| p[0] != p[1] && p[1] != nil}.size
  end