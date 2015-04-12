class Hamming
   def compute(itemA, itemB)
      # if we are the same length, run the test
      if itemA.length == itemB.length
         count = 0
         0.upto(itemA.length) { |i|
            #compare and tick
            if itemA[i] != itemB[i]
               count = count + 1
            end
         }   
         return count
      end
      #all else fails, return error
      return -1
   end
end
