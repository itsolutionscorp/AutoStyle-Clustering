class Hamming
  def compute(string, other_string)
    string.chars.zip(other_string.chars).count { |(a, b)| a != b }
  end
end
