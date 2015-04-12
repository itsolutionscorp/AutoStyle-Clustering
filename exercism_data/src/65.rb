def compute(strand1, strand2)
    distance = 0
    [strand1.length, strand2.length].min.times { |i| distance += 1 if strand1[i] != strand2[i] }
    distance
  end