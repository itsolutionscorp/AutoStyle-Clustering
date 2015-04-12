def compute(a,b)
    (0...([a.length,b.length].min)).reduce(0) { |sum, i| sum + (a[i] != b[i] ? 1 : 0) }
  end