def compute(strand1, strand2)
    strand1
      .chars
      .zip(strand2.chars)
      .count { |item| item[0] != item[1] }
  end