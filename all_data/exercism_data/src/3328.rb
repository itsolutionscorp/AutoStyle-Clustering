def compute(item_a, item_b)
    pairs = item_a.chars.zip(item_b.chars)
    pairs.count { |(a, b)| a != b }
  end