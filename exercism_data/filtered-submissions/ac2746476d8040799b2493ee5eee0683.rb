def compute (a, b)
    diff = 0
    value = [a.length(),b.length()].min
    for i in 0..(value-1)
      diff += 1 if a[i] != b[i]
    end

   diff
  end