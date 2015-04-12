def compute(a, b)
    a.chars.map.with_index { |x, i| b[i] == x ? 0 : 1 }.inject(:+)
  end