def compute(strand1, strand2)
    common_length = [strand1.size, strand2.size].min

    (0...common_length).count { |i| strand1[i] != strand2[i] }
  end