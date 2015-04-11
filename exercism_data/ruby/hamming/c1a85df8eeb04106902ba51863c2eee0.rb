class Hamming
  def self.compute(str1, str2)
    dist = 0
    i = 0
    while i < str1.length and i < str2.length do 
      if not str1[i].eql? str2[i] then
        dist += 1
      end
      i += 1
    end
    dist
  end
end
