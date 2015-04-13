def compute(item_a, item_b)

    hamming_pairs = item_a.chars.zip(item_b.chars)

    hamming_pairs.map { |pair| pair.first != pair.last ? 1 : 0 }.reduce(:+)
  end