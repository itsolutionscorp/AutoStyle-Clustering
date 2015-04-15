def compute(dna1, dna2)
    count = 0
    if dna1.length > dna2.length
      return compute(dna2, dna1)
    end
    dna1.each_char.with_index do |character, index|
      if dna2[index] != character
        count += 1
      end
    end
    count
  end