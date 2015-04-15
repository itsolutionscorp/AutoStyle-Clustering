def compute(strand, other)
    strand.chars.take(other.length).zip(other.chars).count { |(a, b)| a != b }
  end