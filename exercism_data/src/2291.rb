module Hamming
  def compute (a, b)
    a.chars.zip(b.chars).count { |n| n[1] && n[0] != n[1] }
  end
end
