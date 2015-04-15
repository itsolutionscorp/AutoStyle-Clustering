def compute(left, right)
    pairs = left.chars.take(right.length).zip(right.chars)
    pairs.count { |(x, y)| x != y }
  end