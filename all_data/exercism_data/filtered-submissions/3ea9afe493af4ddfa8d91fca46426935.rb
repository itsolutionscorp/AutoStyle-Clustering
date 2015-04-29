def compute(strand_1, strand_2)
    min_strand_length = [strand_1.length, strand_2.length].min
    min_strand_length.times.count { |i| strand_1[i] != strand_2[i] }
  end