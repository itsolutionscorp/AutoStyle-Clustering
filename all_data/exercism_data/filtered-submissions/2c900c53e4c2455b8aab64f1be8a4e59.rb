def compute(string1, string2)
      distance = 0
      string1.each_char.with_index do |char, index|
         if char != string2[index]
            distance = distance +1
         end
      end
      return distance
   end