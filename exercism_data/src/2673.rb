def compute(strand_one, strand_two)
    return 0 if strand_one == strand_two

    shortest_length = [strand_one.size, strand_two.size].min
    hamming_dist = 0

    shortest_length.times do |i|
      hamming_dist += 1 if strand_one[i] != strand_two[i]
    end

    hamming_dist
  end