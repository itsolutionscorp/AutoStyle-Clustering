def compute(strand_1, strand_2)

    range = (0...[strand_1.length, strand_2.length].min)


    points_1 = strand_1[range].chars
    points_2 = strand_2[range].chars


    pairs = points_1.zip points_2


    pairs.count { |p1, p2| p1 != p2 }
  end