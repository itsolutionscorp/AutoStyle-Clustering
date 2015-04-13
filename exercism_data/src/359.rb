def compute(strand1, strand2)
      distance = 0

      test_size = strand1.length <= strand2.length ? strand1.length : strand2.length
      (0...test_size).each do |i|
        unless strand1[i] == strand2[i]
          distance += 1
        end
      end
      return distance
    end