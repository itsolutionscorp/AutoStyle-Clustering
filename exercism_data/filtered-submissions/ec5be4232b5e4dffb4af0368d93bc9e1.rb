def compute(a, b)
    diff = 0
    (0..([a.length, b.length].min - 1)).each do |i|
      diff += 1 if a[i] != b[i]
    end
    diff
  end