def compute(dna1, dna2)


    length = [dna1.length, dna2.length].min


    distance = 0
    0.upto(length - 1) do |i|
      distance += 1 if dna1[i] != dna2[i]
    end

    return distance
  end