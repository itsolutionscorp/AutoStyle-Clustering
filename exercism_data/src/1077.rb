module Hamming
  def self.distance *sequences
    (sequence_a, sequence_b) = sequences.map &:chars
    common_length = sequences.map(&:size).min
    sequence_a.zip(sequence_b).take(common_length).count{|a, b| a != b }
  end
end
