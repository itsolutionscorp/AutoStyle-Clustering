module Hamming
  def self.compute(left, right)
    (0 .. [left.size, right.size].min - 1).count{ |i| left[i] != right[i] }
  end
end
