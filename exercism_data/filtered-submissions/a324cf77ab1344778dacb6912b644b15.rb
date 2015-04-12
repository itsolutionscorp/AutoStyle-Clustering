def compute(a, b)
    min = [a.length, b.length].min
    (0...min).inject(0) { |diff, i| a[i] != b[i] ? diff + 1 : diff }
  end