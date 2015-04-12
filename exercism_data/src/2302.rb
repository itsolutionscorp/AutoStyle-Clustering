def compute(left, right)
    left.chars.zip(right.chars).map { |(a, b)| a == b ? 0 : 1 }.inject(:+)
  end