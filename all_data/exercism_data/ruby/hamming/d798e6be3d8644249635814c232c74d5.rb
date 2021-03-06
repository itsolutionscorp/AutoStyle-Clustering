module Hamming

  def self.compute(s1, s2)
    s1.split('').zip(s2.split('')).count { |(a,b)| b && a != b }
  end
end
