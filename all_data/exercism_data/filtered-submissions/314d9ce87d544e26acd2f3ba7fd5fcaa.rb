def compute(sequence1, sequence2)
    distance = 0
    sequence1.chars.each_with_index do |gene, index|
      distance += 1 unless gene == sequence2[index]
    end
    distance
  end