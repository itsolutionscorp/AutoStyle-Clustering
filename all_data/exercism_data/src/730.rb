def compute(x, y)
    res = 0
    for i in 0..(x.size)
      res += 1 if x[i] != y[i]
    end
    res
  end