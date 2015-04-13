def compute(a,b)
    return -1 if a.size != b.size

    diff = 0


    a.size.times do |i|
      diff += 1 if a[i] != b[i]
    end

    diff
  end