def compute(a, b)
    a.each_char.zip(b.each_char).count { |c1, c2| c1 != c2 }
  end