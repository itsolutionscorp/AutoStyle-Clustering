def compute(a,b)
    return 0 if a == b
    (0...[a.length,b.length].min).inject(0) { |d,n| d += a[n] == b[n] ? 0 : 1 }
  end