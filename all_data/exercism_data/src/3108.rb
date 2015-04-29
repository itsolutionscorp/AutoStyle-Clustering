def compute(strand_1, strand_2)
    strand_size = [strand_1.size, strand_2.size].min
    0.upto(strand_size).count { |i| strand_1[i] != strand_2[i] }
  end