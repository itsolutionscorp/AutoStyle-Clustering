def compute(a, b)
    len = [a.length, b.length].min
    hamming_distance = 0

    (0...len).each do |i|
      hamming_distance += 1 if a[i] != b[i]
    end

    return hamming_distance
  end