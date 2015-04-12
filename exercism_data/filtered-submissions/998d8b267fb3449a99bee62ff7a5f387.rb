module Hamming
  def compute(a, b)
    length = [a.size, b.size].min
    length.times.count { |i| a[i] != b[i] }
  end
end
