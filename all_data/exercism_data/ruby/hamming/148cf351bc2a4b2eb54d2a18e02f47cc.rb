module Hamming
  def self.compute(str1,str2)
    short_str = str1.length <= str2.length ? str1 : str2
    count = 0
    0.upto(short_str.length-1) do |i|
      count += 1 if str1[i] != str2[i]
    end
    count
  end
end
