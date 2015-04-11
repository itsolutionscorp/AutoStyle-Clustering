 module Raindrops
   def convert number
     result = ''
     result << 'Pling' if number.evenly_divisible_by 3
     result << 'Plang' if number.evenly_divisible_by 5
     result << 'Plong' if number.evenly_divisible_by 7
     result.empty? ? number.to_s : result
   end


 end
 class Fixnum
   def evenly_divisible_by number
     (self % number).zero?
   end

 end
 Raindrops.extend(Raindrops)
