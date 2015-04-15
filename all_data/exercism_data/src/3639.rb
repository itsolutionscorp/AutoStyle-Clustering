def compute(x, y)
    res = 0
    x.chars.each_with_index { |xi, i| res += 1 if xi != y[i] }
    res
  end