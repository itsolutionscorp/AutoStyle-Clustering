class Hamming
  def self.compute(first_string, second_string)
    first_string.chars.zip(second_string.chars)
      .map { |(a, b)| a == b ? 0 : 1 }
      .reduce(&:+)
  end
end
