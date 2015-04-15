def compute (strand_a, strand_b)
    # compute and return the hamming distance between two strands
    [strand_a.size, strand_b.size].min.times.count { |i| strand_a[i] != strand_b[i] }
  end

end