# encoding: UTF-8
# Clase Hamming
class Hamming
  def self.compute(string1, string2)
    sum = 0
    if string2.length == string1.length
      (0...[string1.length, string2.length].min).count do |i|
        sum += 1 if string1[i] != string2[i]
      end
    end
    sum
  end
end
