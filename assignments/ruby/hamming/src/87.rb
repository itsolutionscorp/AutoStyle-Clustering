def compute(first_strand, second_strand)


    if first_strand.length  != second_strand.length
      raise "The strand lengths are not the same."
    end




    distance = 0





    first_strand.chars.zip(second_strand.chars).each do |base_one, base_two|
      if base_one != base_two
        distance += 1
      end
    end
    return distance
  end