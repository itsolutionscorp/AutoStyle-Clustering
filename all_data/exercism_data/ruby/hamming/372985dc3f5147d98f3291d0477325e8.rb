class Hamming
  def self.compute(str1, str2)
    len = [str1.length, str2.length].min
    answer = 0
    len.times do |i|
      if str1[i] != str2[i] 
        answer += 1
      end
    end
    answer
  end
end
