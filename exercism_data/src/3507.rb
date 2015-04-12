def compute(strand1, strand2)
    shorter_length = [strand1.length, strand2.length].min
    (0...shorter_length).count { |i| strand1[i] != strand2[i] }
  end