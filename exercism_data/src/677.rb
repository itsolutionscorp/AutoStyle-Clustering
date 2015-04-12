def compute(s1,s2)
    pairs = s1.chars.zip(s2.chars)
    return pairs.select{|n| n[0] != n[1] }.length
  end