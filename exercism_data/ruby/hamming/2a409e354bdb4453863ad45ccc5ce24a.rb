module Hamming
  extend self

  def compute(s1, s2)
    pairs = s1.chars.zip(s2.chars)

    pairs
      .reject { |a, b| a.nil? || b.nil? }
      .count { |a, b| a != b }
  end
end
