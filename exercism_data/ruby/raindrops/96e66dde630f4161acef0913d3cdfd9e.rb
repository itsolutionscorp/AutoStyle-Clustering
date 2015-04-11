class Raindrops  
     def self.convert (number)     
      string = ""
      
      # check for number is divisible by 3,5 or 7
      if number%3 == 0
       string += "Pling"
      end
      if number%5 == 0
       string += "Plang"
      end
      if number%7 == 0
       string += "Plong"
      end
      
      # if non matched then return number instead of pling/plang/plong
      if string.empty?
        # for some reason it fails 3 of these tests even tho it returns the exact same number!?
        number
      else
       string        
      end
     end
end
