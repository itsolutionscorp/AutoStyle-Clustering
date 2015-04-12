def compute(strA, strB)
      count = 0
      result = 0
      #iterate through strA chars and compare to strB; 
      strA.each_char do |char|
        if char != strB[count]
          result += 1
        end
        count += 1
      end
      return result