def compute(string_a, string_b)
    up_string_a, up_string_b  = string_a.upcase, string_b.upcase
      array_1 = up_string_a.split(//)
      array_2 = up_string_b.split(//)
      hamming_distance = 0
      index = 0

      array_1.each do |x|
        if !x.eql?(array_2[index])
          hamming_distance += 1
        end
       if (index+1 == array_2.length)then  break
       end
           index += 1
      end
    return hamming_distance
    end