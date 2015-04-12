def compute(a,b)
    total = 0
    [a.length, b.length].min.times {|n| total += 1 if a[n] != b[n]}
    total
  end