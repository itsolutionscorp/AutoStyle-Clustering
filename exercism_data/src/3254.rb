def compute(strand_one, strand_two)
    distance = 0

    strand_one.chars.each_with_index do |code, index|
      break unless strand_two[index] != nil 
      distance += 1 if strand_two[index] != code
    end

    distance
  end