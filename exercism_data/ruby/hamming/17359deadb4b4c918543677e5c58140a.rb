class Hamming
  def self.compute(a, b)
    [a, b].map(&:chars).transpose.select{ |x, y| x != y }.count
  end
end
