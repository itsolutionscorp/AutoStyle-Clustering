def compute(strand1, strand2)
      distance = 0

      (0..strand1.length).each do |index|
        distance += 1 unless strand1[index] == strand2[index]
      end

      return distance

  end