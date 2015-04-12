def compute(strand_a, strand_b)
    distance = 0
    strand_a.each_char.zip(strand_b.each_char) do |base_of_strand_a, base_of_strand_b|
      break if base_of_strand_b == nil
      distance += 1 if base_of_strand_a != base_of_strand_b
    end
    distance
  end