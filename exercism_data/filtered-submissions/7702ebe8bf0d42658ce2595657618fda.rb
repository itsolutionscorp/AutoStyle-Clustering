# Hamming Distance
# Author: t0nyLombardi
# 01/22/15

class Hamming

  def compute(s1, s2)
    hamming = 0
    s1.each_char do |i|
      hamming += 1 unless s1[i] == s2[i]
    end
    hamming
  end

end
