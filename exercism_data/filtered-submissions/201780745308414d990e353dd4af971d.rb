class Hamming
   def compute(actual, expected)
      count = 0
      for pos in 0..actual.length - 1
         if actual[pos] != expected[pos]
            count += 1
         end
      end
      return count
   end
end
