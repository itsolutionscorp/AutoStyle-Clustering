def compute(a, b)
    [a.chars, b.chars].transpose.map { |x, y| x == y ? 0 : 1 }.reduce(:+)
  end