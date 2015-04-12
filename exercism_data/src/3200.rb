def compute(a, b)
    n = [a.length, b.length].min

    n.times.count do |i|
      a[i] != b[i]
    end
  end