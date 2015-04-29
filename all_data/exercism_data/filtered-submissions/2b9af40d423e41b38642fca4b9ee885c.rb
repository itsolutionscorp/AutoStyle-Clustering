module Hamming
  def Hamming.compute (a, b)
    a = a.split ''
    b = b.split ''
    count = 0
    (0...a.length).each do |i|
      if a[i] != b[i]
        count += 1
      end
    end
    count
  end
end
