class Hamming

  def self.compute(strand, other)
    pairs(strand, other).count { |a, b| a != b }
  end

  private

  def self.pairs(strand, other)
    strand.chars.zip(other.chars).take(other.length)
  end

end
