def compute(dna1, dna2)

    distance = 0

    dna1.length.times do |check|

      index = check - 1

      if dna1[index] != dna2[index]
        distance += 1
      end

    end

    distance

  end