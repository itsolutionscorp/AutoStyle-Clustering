class Hamming
  def compute strand_a, strand_b
    strand_a
      .chars
      .zip(strand_b.chars)
      .count { |char_a, char_b| char_b && char_b != char_a }
  end
end
