module Hamming
  def Hamming.compute(a, b)
    (0...[a.length, b.length].min).count { |i| a[i] != b[i] }
  end
end
