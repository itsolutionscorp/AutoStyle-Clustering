def compute(a, b)
    size = [a.length, b.length].max
    count = 0
    size.times do |i|
      if a[i] != b[i]
        count += 1
      end
    end

    count
  end