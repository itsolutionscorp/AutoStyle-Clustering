class Hamming
  def compute(a, b)
    return 0 if a == b
    pairs = a.chars.zip(b.chars)
    pairs.reduce(0) { |acc, (a, b)| acc + (a == b ? 0 : 1) }
  end
end
