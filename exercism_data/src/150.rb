def compute(a, b)
  	diff = 0
    n = [a.length, b.length].min

    n.times do |i|
      diff += 1 if a[i] != b[i]
    end

    diff
  end