def compute(strand_1, strand_2)
    len = [strand_1.size, strand_2.size].min
    point_mutations = 0

    for i in 0..(len-1)
      point_mutations += 1 unless strand_1[i] == strand_2[i]
    end

    point_mutations
  end