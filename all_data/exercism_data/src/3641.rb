def compute(string_a, string_b)
    up_string_a, up_string_b  = string_a.upcase, string_b.upcase
      array_1 = up_string_a.split(//) #converting the String to an array
      array_2 = up_string_b.split(//)
      hamming_distance = 0 #counting the different letters
      index = 0 #index in 2nd array
      
      array_1.each do |x| #for each letter in the first parameter
        if !x.eql?(array_2[index]) 
          hamming_distance += 1 #if different, count
        end 
       if (index+1 == array_2.length)then  break #break if first array is longer
       end
           index += 1 #move forward
      end
    return hamming_distance
    end