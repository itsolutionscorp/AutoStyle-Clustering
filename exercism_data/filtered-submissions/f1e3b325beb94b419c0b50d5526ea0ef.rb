class Hamming
   def compute(string1, string2)
      distance = 0
      string1.each_char.with_index do |char, index|
         distance += ( char != string2[index] ? 1 : 0 )
      end
      return distance
   end
end
