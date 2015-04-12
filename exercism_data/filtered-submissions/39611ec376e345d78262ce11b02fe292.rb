def compute(dna1, dna2)

    # Find shorter length and use that
    length = [dna1.length, dna2.length].min

    # Compare characters
    distance = 0
    length.times do |i|
      distance += 1 if dna1[i] != dna2[i]
    end

    return distance
  end