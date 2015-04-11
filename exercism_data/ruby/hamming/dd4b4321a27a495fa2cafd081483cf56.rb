# Hamming class
class Hamming
  def self.compute(str1, str2)
    dist = 0
    i = 0
    while i < str1.length && i < str2.length
      dist += 1 unless str1[i].eql? str2[i]
      i += 1
    end
    dist
  end
end
