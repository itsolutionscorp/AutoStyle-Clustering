def compute(str1, str2)
    
    str1.length <= str2.length ? gataca_counter = str1.length : gataca_counter = str2.length
    
    hamming = 0
    
    gataca_counter.times do |n|
      if str1[n] != str2[n]
        hamming += 1
      end
    end
    
    hamming
  end