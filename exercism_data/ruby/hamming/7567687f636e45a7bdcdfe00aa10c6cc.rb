module Hamming
  def self.compute(s1, s2)
    distance = 0

    if (s1.length <= s2.length)
      strand_1 = s1
      strand_2 = s2
    else
      strand_1 = s2
      strand_2 = s1
    end
    
    strand_1.chars.each_with_index do |x1, i|
      distance += 1 if x1 != strand_2[i]
    end
    distance
  end
end
