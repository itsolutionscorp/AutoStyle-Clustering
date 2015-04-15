def compute(dna1, dna2)
    return 0 if dna1 == dna2
    return 0 unless dna1.length == dna2.length
    difference = 0
    dna1.chars.each_with_index do |item, index|
      difference += 1 if dna2[index] != item
    end
    return difference
  end