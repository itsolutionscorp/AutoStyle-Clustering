def compute(strand1, strand2)
    h_distance = 0
    strand1.split("").each_with_index do |c,i|
      h_distance += 1 if c != strand2[i]
    end
    return h_distance
  end