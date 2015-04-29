class Hamming
  def self.compute first_strand, second_strand
    hamming_count = 0

    if first_strand.length < second_strand.length
      test_length = first_strand.length
    else
      test_length = second_strand.length
    end


    test_length.times do |i|
      if first_strand[i] != second_strand[i]
        hamming_count += 1
      end
    end

    hamming_count

  end
end
