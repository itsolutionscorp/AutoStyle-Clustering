module Hamming
  def compute (a, b)
    a.chars.zip(b.chars).count { |x, y| y && x != y }
  end
end
