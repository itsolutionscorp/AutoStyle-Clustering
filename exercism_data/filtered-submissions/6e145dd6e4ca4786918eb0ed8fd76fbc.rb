def compute(strand_1, strand_2)

    range = (0...[strand_1.length, strand_2.length].min)


    points_1 = strand_1[range].split ''
    points_2 = strand_2[range].split ''


    pairs = points_1.zip points_2


    pairs.count { |pair| pair[0] != pair[1] }
  end