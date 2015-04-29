module Hamming
  def Hamming.compute(a, b)
    (0...[a.length, b.length].min).inject(0) do |s, i|
      a[i] == b[i] ? s : s + 1
    end
  end
end
