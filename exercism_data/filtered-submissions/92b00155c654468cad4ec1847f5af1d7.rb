def compute(dna1, dna2)


    dna1.upcase!
    dna2.upcase!


    length = (dna1.length <= dna2.length) ? dna1.length : dna2.length


    distance = 0
    0.upto(length - 1) do |i|
      distance += 1 if dna1.slice(i, 1) != dna2.slice(i, 1)
    end

    return distance
  end