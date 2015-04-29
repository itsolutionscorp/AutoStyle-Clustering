class Hamming
  self.compute(str1, str2)
    count = 0
    str1.size.times {|i| count+=1 if str1[i] != str2[i]}
    count
  end
end
