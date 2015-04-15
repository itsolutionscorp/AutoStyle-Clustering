def compute(dna1, dna2)

    # Normalize dna strings
    dna1.upcase!
    dna2.upcase!

    # Find shorter length and use that
    length = (dna1.length <= dna2.length) ? dna1.length : dna2.length

    # Compare characters
    distance = 0
    0.upto(length - 1) do |i|
      distance += 1 if dna1.slice(i, 1) != dna2.slice(i, 1)
    end

    return distance
  end