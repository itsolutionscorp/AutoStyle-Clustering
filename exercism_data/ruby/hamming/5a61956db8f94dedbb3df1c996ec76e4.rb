class Hamming

  def self.compute(str1, str2)
    len=self.last_index(str1, str2)
    distance(str1, str2, len)
  end
  
  def self.last_index(str1, str2)
    [str1.length, str2.length].min-1
  end
  
  def self.distance(str1, str2, len)
    dist=0
    for i in 0..len
      dist+=1 if str1[i]!=str2[i]
    end
    dist
  end
  
end
