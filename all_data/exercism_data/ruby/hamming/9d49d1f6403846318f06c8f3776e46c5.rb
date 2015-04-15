module Hamming
  def self.compute(strand, other_strand)
    min_length = [strand.size, other_strand.size].min
    chars = normalize(strand, min_length)
    other_chars = normalize(other_strand, min_length)

    chars.zip(other_chars).count { |a, b| a != b }
  end

  private

  def self.normalize(strand, length)
    strand.chars.take(length)
  end
end
