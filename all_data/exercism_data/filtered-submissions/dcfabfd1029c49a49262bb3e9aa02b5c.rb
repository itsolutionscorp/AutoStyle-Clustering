def compute (strand_a, strand_b)

    [strand_a.size, strand_b.size].min.times.count { |i| strand_a[i] != strand_b[i] }
  end