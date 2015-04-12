def compute first_strand, second_strand
    hamming_count = 0

    test_length = [first_strand.length, second_strand.length].min

    test_length.times do |i|
      hamming_count += 1 if first_strand[i] != second_strand[i]
    end

    hamming_count
  end