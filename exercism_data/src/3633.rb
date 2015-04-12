def compute(strand1, strand2)
    smallest_strand = [strand1.size, strand2.size].min
    smallest_strand.times.count { |i| strand1[i] != strand2[i] }
  end