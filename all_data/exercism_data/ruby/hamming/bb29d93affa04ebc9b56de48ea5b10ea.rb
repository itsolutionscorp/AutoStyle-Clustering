class Hamming
  def self.compute(a, b)
    return 0 if a == b

    positions = a.chars.zip(b.chars)
    errors = positions.find_all { |(x, y)| y && x != y }

    errors.length
  end
end
