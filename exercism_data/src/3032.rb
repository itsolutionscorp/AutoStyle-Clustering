def compute(a, b)
    hamming_distance = 0

    a.each_char.with_index do |a_char, i|
      hamming_distance += 1 if a_char != b[i]
    end
    hamming_distance
  end