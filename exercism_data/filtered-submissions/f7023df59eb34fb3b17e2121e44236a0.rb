module Hamming
  def compute(a, b)
    return 0 if a == b
    a.chars.take(b.size).zip(b.chars).count { |x,y| x != y }
  end
end
