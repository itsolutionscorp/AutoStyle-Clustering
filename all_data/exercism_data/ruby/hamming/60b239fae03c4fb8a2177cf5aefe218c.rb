module Hamming
  def self.compute(a, b)
    a, b = b, a if a.length > b.length
    a.chars.zip(b.chars).count { |char_a, char_b| char_a != char_b }
  end
end
