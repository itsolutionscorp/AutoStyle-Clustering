class Hamming
  def compute(first_string, second_string)
    first_string.chars.zip(second_string.chars)
      .count { |(a, b)| a != b }
  end
end
