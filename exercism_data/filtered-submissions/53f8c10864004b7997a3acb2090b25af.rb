def compute(a, b)
    distance = 0
    min_len = [a.length, b.length].min

    (0...min_len).each do |i|
      distance += 1 if a[i] != b[i]
    end

    distance
  end