def compute(one, two)
      distance = 0





      for i in 0..(one.length - 1)
         if (one[i] != two[i]) then
            distance = distance + 1
         end
      end
      return distance
   end