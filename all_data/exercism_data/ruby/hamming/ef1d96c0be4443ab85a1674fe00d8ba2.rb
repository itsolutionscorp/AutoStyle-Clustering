class Hamming
  def self.compute(strand, other)
    positional_pairs(strand, other).count { |a, b| a != b }
  end

  class << self
    private

    def positional_pairs(strand, other)
      strand.chars.zip(other.chars).take(other.size)
    end
  end
end
