class Raindrops
   def self.convert(argument)
      my_output = ""
      if argument%3 == 0
         my_output << 'Pling'
      end

      if argument%5 == 0
         my_output << 'Plang'
      end

      if argument%7 == 0
         my_output << 'Plong'
      end

      if argument%3 != 0 && argument%5 != 0 && argument%7 != 0
         my_output = argument.to_s
      end

      my_output
   end

end
