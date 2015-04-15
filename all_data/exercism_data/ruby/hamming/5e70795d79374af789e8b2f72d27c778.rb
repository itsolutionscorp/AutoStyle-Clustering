# encoding: UTF-8
# Clase Hamming
class Hamming
  def self.compute(str1, str2)
    return 0 if str1.length != str2.length
    count = 0
    for i in 0..str1.length
      count += 1 if str1[i] != str2[i]
    end
    count
  end
end
