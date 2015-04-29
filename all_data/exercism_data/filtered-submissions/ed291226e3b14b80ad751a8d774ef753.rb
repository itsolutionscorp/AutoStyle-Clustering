def compute(first_strand, second_strand)

    raise "Both strand strings must be non-null" if first_strand.nil? or second_strand.nil?

    distance = 0

    for i in 0...[first_strand.length, second_strand.length].min
      distance += 1 if first_strand[i] != second_strand[i]
    end

    distance
  end