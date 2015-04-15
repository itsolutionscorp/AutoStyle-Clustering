class Hamming
  def self.compute(first, second)
    pairs = first.codepoints.zip(second.codepoints)
    pairs.count { |x, y| x != y  }
  end
end
