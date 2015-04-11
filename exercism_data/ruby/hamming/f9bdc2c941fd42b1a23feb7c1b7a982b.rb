module Hamming
  def self.compute(first, second)
    first.chars
      .zip(second.chars)
      .reject { |pair| pair.any?(&:nil?) }
      .count { |pair| pair[0] != pair[1] }
  end
end
