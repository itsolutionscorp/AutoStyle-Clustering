def compute(s1, s2)
    s1_prefix = s1[0, s2.length]
    s2_prefix = s2[0, s1.length]
    s1_prefix.chars.zip(s2_prefix.chars).count { |c1, c2| c1 != c2 }
  end