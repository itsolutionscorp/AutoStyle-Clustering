def compute(string1, string2)
      distance = 0
      string1.each_char.with_index do |char, index|
         char != string2[index] ? distance += 1 : distance
      end
      return distance
   end