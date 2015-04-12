def compute(strand_a, strand_b)
    distance = 0
    
    strand_a.chars.each_with_index do |base, index|
      distance = distance + 1 if base != strand_b[index]
    end
    distance
  end