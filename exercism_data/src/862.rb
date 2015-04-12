class Hamming
  def compute(str1, str2)
    
    gataca_counter = (str1.length <= str2.length) ? str1.length : str2.length
    
    hamming = 0
    
    gataca_counter.times do |n|
      if str1[n] != str2[n]
        hamming += 1
      end
    end
    
    hamming
  end
end
    
