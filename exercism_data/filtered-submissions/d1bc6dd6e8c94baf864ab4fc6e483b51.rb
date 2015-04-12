def compute(a, b)
    hamming = 0
    a.chars.each_with_index do |char, i|
      hamming += 1 if char != b[i]
    end
    hamming
  end