def compute(first_strand, second_strand)
    # Verify that both strands are not nil
    raise "Both strand strings must be non-null" if first_strand.nil? or second_strand.nil?
    
    distance = 0
    # If one strand is longer than the other, ignore any extra length from the longer strand
    for i in 0...[first_strand.length, second_strand.length].min
      distance += 1 if first_strand[i] != second_strand[i]
    end

    distance
  end