def compute(strand_a, strand_b)


    check_length = [strand_a.length, strand_b.length].min
    (0...check_length).count { |c| strand_a[c] != strand_b[c] }
  end