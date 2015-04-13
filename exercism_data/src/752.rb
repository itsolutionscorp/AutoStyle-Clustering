def compute(a, b)
    strand_a = a.chars.take(b.length)
    strand_b = b.chars.take(a.length)

    pairs = strand_a.zip(strand_b)
    pairs.count { |x, y| x != y }
  end