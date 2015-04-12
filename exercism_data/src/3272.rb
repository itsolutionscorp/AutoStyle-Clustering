def compute(a,b)
    return 0 if a == b
    distance = 0
    0.upto([a.length,b.length].min - 1) {|n| distance += 1 if a[n] != b[n] }
    distance
  end