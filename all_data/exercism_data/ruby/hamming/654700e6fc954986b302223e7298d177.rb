module Hamming
  extend self

  def compute(strand, other)
    positional_pairs(strand, other).count { |a, b| a != b }
  end

  private

  def positional_pairs(strand, other)
    strand.chars.zip(other.chars).take(other.size)
  end
end
