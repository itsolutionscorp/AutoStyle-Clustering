def compute(first, second)
    first
      .chars
      .zip(second.chars)
      .count { |char_pair| char_pair[0] != char_pair[1] }
  end