module Hamming
  def self.distance sequence_a, sequence_b
    sequence_a.chars
    .zip(sequence_b.chars)
    .take(common_length(sequence_a,
                        sequence_b))
    .count{|a, b| a != b }
  end

  def self.common_length *sequences
    sequences.map(&:size).min
  end
end
