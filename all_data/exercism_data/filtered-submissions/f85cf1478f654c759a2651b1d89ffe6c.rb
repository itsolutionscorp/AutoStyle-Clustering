def compute(strand_one, strand_two)
    distance = 0

    strand_one.split("").each_with_index do |code, index|
      break unless strand_two[index] != nil
      distance += (code == strand_two[index] ? 0 : 1)
    end

    distance
  end