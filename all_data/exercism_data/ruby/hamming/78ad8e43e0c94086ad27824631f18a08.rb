class Hamming
   def self.compute(string1, string2)
      distance = string1.split("").zip(string2.split("")).inject(0) do |result, element|
         element.first == element.last ? result += 0 : result += 1
      end
   end
end
