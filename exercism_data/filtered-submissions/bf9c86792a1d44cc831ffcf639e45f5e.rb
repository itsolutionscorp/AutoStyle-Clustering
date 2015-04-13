def compute(a, b)

    distance = 0


    (0..[a.length, b.length].max).each do |i|
      next if i + 1 > a.length or i + 1 > b.length
      distance += 1 unless a[i] == b[i]
    end

    distance
  end