def compute(strand_one, strand_two)
    hamming_distance = 0
    min_strand_length = [strand_one.length, strand_two.length].min

    (0...min_strand_length).each do | i |
      hamming_distance += 1 if strand_one[i] != strand_two[i]
    end

    hamming_distance
  end
end