def compute(s1, s2)
    s1.chars.map.with_index { |char, index| char == s2[index] ? 0 : 1 }.inject(:+)
  end