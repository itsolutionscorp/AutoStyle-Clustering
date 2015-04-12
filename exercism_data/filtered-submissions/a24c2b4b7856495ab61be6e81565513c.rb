def compute(strA, strB)
      minLen = [strA.length, strB.length].min
      dist = 0
      (0...minLen).each do |i|
        dist += 1 if strA[i] != strB[i]
      end
      dist
    end