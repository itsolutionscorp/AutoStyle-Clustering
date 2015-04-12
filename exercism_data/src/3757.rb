def compute(strand1, strand2)
    length = [strand1.length, strand2.length].min
    length.times.count { |i| strand1[i] != strand2[i] }
  end