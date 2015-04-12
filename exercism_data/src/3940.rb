module Hamming
  def Hamming.compute(a, b)
    [a, b].map(&:length).min.times.count { |i| a[i] != b[i] }
  end
end
