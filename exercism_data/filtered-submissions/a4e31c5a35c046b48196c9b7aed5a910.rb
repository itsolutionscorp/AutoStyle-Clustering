def compute(a, b)
    a.chars.select.with_index{|c,i|b.chars[i]!=c}.count
  end