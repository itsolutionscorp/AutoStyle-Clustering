module Hamming
  def self.distance left, right
    common_length(left, right)
    .times.count { |i| left[i] != right[i] }
  end

  def self.common_length *sequences
    sequences.map(&:size).min
  end
end
