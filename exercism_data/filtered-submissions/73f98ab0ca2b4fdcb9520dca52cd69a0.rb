def compute(strand_1, strand_2)

    dissimilarity_count = 0

    if strand_1.length > strand_2.length
      smallest_string_length = strand_2.length
    elsif strand_1.length < strand_2.length
      smallest_string_length = strand_1.length
    else
      smallest_string_length = strand_1.length
    end

    smallest_string_length.times do |i|
    	binding.pry
      if strand_1[i] != strand_2[i]
        dissimilarity_count += 1
      end
    end

    dissimilarity_count

  end