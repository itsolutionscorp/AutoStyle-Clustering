class Hamming
  def self.compute(a, b)
    pairs = a.chars.zip(b.chars)
    pairs.count { |(a, b)| a != b }
  end
end