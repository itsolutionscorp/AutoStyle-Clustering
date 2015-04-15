def compute (dna_1, dna_2)
    longest = dna_1.length > dna_2.length ? dna_1 : dna_2
    shortest = dna_1.length <= dna_2.length ? dna_1 : dna_2

    distance = 0
    shortest.length.times do |i|
      distance += 1 if longest[i] !=  shortest[i]
    end
    return distance
  end