def compute strand_1, strand_2

    distance = 0
    
    (0...[strand_1.size(), strand_2.size()].min()).each() do |i|
      distance += 1 if strand_1[i] != strand_2[i]
      i += 1
    end

    return distance
  end