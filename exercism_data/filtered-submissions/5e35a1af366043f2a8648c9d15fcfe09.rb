def compute(first_strand, second_strand)
    hamming_distance = 0

    first_strand.length.times do |count|
      if first_strand[count] != second_strand[count]
        unless (first_strand[count] && second_strand[count]).nil?
          hamming_distance += 1
        end
      end
    end
    hamming_distance
  end