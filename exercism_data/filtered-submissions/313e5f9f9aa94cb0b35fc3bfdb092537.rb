def compute(strand_1, strand_2)
    distance = 0
    size = [strand_1.size, strand_2.size].min

    (0...size).each do |i|
      distance += 1 if strand_1[i] != strand_2[i]
    end

    distance
  end