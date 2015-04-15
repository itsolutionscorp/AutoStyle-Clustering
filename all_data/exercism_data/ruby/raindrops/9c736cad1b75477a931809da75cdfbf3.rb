class Raindrops
   def self.convert(argument)
      raindrops = [[3,'Pling'],[5,'Plang'],[7,'Plong']]

      my_output = ""

      raindrops.each do |raindrop|
         my_output << raindrop[1] if argument % raindrop[0] == 0 
      end

      my_output.length == 0 ? argument.to_s : my_output

   end

end
