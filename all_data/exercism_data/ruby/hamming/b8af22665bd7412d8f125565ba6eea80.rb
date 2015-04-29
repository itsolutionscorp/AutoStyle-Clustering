require 'pry'
class Hamming
  def self.compute(s1, s2)
    size = [s1.size, s2.size].min

    distance = 0
    0.upto(size-1).each do |i|
      distance += 1 if s1[i] != s2[i]
    end
    distance
  end
end
