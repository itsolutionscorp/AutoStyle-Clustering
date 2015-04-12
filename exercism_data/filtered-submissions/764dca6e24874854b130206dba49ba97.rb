class Hamming
  def compute(a, b)
    pairs = a.chars.zip(b.chars)
    pairs.select { |(a, b)| a != b }.count
  end
end