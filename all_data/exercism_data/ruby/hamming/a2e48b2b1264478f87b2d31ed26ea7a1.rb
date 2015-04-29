module Hamming

  def self.compute(strand_1, strand_2)
    # smallest strand length defines range points to compare
    range = (0...[strand_1.length, strand_2.length].min)

    # transform to point arrays
    points_1 = strand_1[range].chars
    points_2 = strand_2[range].chars

    # zip arrays to get pairs of points
    pairs = points_1.zip points_2

    # count the mutations
    pairs.count { |pair| pair[0] != pair[1] }
  end

end
