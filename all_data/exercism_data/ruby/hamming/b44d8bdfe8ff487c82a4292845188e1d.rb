class Hamming
  def self.compute(a, b)
    return 0 if a == b

    positions = a.chars.zip(b.chars)

    positions.count { |(x, y)| y && x != y }
  end
end
