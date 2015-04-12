def compute(dna1, dna2)
    distance = 0
    length = [dna1.length, dna2.length].min

    length.times do |i|
      if dna1[i] != dna2[i]
        distance += 1
      end
    end

    distance
  end