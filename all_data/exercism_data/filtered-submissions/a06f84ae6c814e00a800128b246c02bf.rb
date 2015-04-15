def compute(strand_1, strand_2)
    distance = 0

    [strand_1.size, strand_2.size].min.times do |i|
      distance += 1 if strand_1[i] != strand_2[i]
    end

    distance
  end