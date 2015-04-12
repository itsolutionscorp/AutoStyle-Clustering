def compute(dna1, dna2)
    fail ArgumentError, 'Argument lengths are not equal' unless dna1.size == dna2.size
    return 0 if (dna1 == dna2)
    total = 0
    dna1.each_char.with_index do |char, index|
      total += 1 if (char != dna2[index])
    end
    total
  end