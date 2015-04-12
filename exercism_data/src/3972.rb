def compute (strandA, strandB)
      count = [strandA.length, strandB.length].min
      dist = 0
      count.times do |i|
        dist += (strandA[i] != strandB[i] ? 1 : 0)
      end

      return dist
    end