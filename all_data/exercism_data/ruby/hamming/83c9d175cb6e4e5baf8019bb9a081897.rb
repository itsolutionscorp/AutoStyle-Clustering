class Hamming
  def self.compute(lhs, rhs)
    strands = lhs.chars.zip(rhs.chars)
    strands.inject(0) { |diff, pair| diff + (pair[0] != pair[1] ? 1 : 0) }
  end
end
