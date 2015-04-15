module Hamming
  def Hamming.compute(a, b)
    l = [a.length, b.length].min
    aa = a.split("")[0, l]
    bb = b.split("")[0, l]

    (0...l).inject(0) do |s, i|
      aa[i] == bb[i] ? s : s + 1
    end
  end
end
