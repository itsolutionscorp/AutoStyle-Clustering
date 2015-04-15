def compute(strand_1, strand_2)
    length = strand_1.length
    length.times.count { |i| strand_1[i] != strand_2[i] }
  end