def compute(first, second)
    hamming_distance = 0

    (0..first.size).each do |i|
      hamming_distance += 1 if first[i] != second[i]
    end
    hamming_distance
  end