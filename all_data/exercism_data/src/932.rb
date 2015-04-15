def compute(strand1, strand2)
    index = 0

    differences = 0

    while index < strand1.length && index < strand2.length
      unless strand1[index] == strand2[index]
        differences += 1
      end

      index += 1
    end

    differences
  end