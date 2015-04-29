def compute(a, b)

    length = [a.length, b.length].min
    difference = 0

    length.times do |i|
      diff += 1 if a[i] != b[i]
    end

    difference
  end