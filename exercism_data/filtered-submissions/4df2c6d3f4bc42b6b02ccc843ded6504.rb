def compute(strA, strB)
      minLen = min strA.length, strB.length
      dist = 0
      (0...minLen).each do |i|
        dist += 1 if strA[i] != strB[i]
      end
      dist
    end