def compute(itemA, itemB)

      if itemA.length == itemB.length
         count = 0
         0.upto(itemA.length) { |i|

            if itemA[i] != itemB[i]
               count = count + 1
            end
         }
         return count
      end

      return -1
   end