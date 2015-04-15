module Hamming
  def self.compute(s1, s2)
    distance = 0
    (0..(s1.length)).each do |i|
      c1 = s1[i]
      c2 = s2[i]
      distance += 1 if c1 != c2
    end
    distance
  end
end
