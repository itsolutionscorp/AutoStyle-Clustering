def compute(a, b)
    l = a.length > b.length ? a.length : b.length
    d = 0

    l.times do |i|
      d += 1 if a[i] != b[i]
    end

    d
  end