def compute(strand_a, strand_b)
    distance = 0

    strand_a.chars.each_with_index do |point,index|
      if strand_b[index] && point != strand_b[index]
        distance += 1
      end
    end

    distance
  end