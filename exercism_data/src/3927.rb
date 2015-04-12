# http://en.wikipedia.org/wiki/Hamming_distance
module Hamming
  def compute(s1, s2)
    s1 = s1[0, s2.length]
    s2 = s2[0, s1.length]
    s1.chars.zip(s2.chars).count { |c1, c2| c1 != c2 }
  end
end
