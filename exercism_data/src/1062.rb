def compute(a, b)

    size = (a.size <= b.size) ? a.size : b.size

    hamming_distance = 0

    (0..size-1).each do |i|
      if a[i]!=b[i]
        hamming_distance += 1
      end
    end

    return hamming_distance
  end