class Hamming
  def compute(str1, str2)
    hamming_result = 0
    compare_length = str1.size < str2.size ? str1.szie : str2.size
    compare_length.times do |index| 
      if str1[index] != str2[index]
        hamming_result += 1
      end
    end
    hamming_result 
  end
end
