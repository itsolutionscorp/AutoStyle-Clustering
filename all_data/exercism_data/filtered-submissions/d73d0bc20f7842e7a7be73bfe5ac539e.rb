def compute(left_strand, right_strand)
    left_array = left_strand.split("")
    right_array = right_strand.split("")


    left_array.each_with_index do |value, index|
      if value != right_array[index] && index < right_array.count
        count += 1
      end
    end
    count
  end