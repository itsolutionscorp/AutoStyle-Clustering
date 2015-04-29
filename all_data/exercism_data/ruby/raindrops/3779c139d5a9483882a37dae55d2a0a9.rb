class Raindrops
   def self.convert(argument)
      raindrops = [[3,'Pling'],[5,'Plang'],[7,'Plong']]

      my_output = ""

      raindrops.each { |number, word| my_output << word if argument % number == 0 }

      my_output.length == 0 ? argument.to_s : my_output

   end

end
