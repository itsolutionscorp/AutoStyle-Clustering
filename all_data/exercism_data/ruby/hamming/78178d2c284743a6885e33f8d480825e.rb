module Hamming
  def self.compute a_str, b_str
    dist = 0
    a, b = a_str.split(''), b_str.split('')
    for i in 0...a.length
      dist += 1 if a[i] != b[i]
    end
    dist
  end
end
