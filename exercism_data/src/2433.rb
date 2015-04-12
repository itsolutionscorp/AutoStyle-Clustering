def compute(a,b)
    h = 0
    for pos in 0..(a.length - 1)
      h+= 1 if a[pos] != b[pos]
    end
    h
  end