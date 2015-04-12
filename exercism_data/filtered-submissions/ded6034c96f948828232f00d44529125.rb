def compute(a, b)
    a.chars.map.with_index { |char, index| b[index] == char }.count { |match| !match }
  end