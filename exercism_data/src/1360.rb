module Hamming
  def Hamming.compute(a, b)
    (0...[a.length, b.length].min).reject do |i|
      a[i] == b[i]
    end.size
  end
end
