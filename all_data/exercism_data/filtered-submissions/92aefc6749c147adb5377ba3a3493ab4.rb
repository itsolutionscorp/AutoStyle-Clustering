def compute(a,b)
    a.chars.zip(b.chars).inject(0){|x,y| x + (y.inject(&:==) ? 0 : 1)}
  end