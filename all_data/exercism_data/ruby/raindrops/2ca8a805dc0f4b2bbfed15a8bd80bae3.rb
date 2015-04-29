class Raindrops
   def self.convert(argument)
      raindrops = [[3,'Pling'],[5,'Plang'],[7,'Plong']]

      msg = raindrops.map { |number, word| word if argument % number == 0 }.join

      msg.length == 0 ? argument.to_s : msg

   end

end
