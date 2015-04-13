def compute(first_strand, second_strand)

    raise "Both strand strings must be non-null" if first_strand.nil? or second_strand.nil?

    distance = 0
    first_strand_array = first_strand.split(//)
    second_strand_array = second_strand.split(//)


    for i in 0..[first_strand_array.length, second_strand_array.length].min-1
      distance += 1 if first_strand_array[i] != second_strand_array[i]
    end

    distance
  end