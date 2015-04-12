def compute(a, b)
    diff = 0
    [a.length, b.length].min.times do |i|
        diff = diff + 1 unless a[i] == b[i]
    end
    diff
  end