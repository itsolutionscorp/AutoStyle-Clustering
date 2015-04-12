def compute(first_strand, second_strand)
    # Ensure the DNA strand lengths are the same for
    # comparison to occur
    if first_strand.length  != second_strand.length
      raise "The strand lengths are not the same."
    end

   

    # Set distance to zero
    distance = 0

    # Iterate over the two strand character arrays adding the 
    # index from each to a key/value pair in the new array for 
    # easier comparison. Each time the new key/value pair is not
    # equal, add 1 to the hamming.distance
    first_strand.chars.zip(second_strand.chars).each do |base_one, base_two|
      if base_one != base_two
        distance += 1
      end
    end
    return distance
  end