def compute(prim, sec)
    return 0 if prim == sec
    a = prim.to_a
    b = sec.to_a
    a.zip(b).select do |(x, y)|
      x != y if y && x
    end.count
  end