def compute(strand1, strand2)
    distance = 0
    (0..strand1.size).each do |i|
      distance += 1 if strand1[i] and strand2[i] and strand1[i] != strand2[i]
    end
    return distance
  end