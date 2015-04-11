module Hamming
  def self.compute(a, b)
    length = [a.size, b.size].min
    length.times.count { |i| a[i] != b[i] }
  end
end
