def compute(strand1, strand2)
    distance = 0
    strand1.each_char.with_index do |char, index|
      distance += 1 if char != strand2[index]
    end
    return distance
  end