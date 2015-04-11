class Hamming
  def self.compute first_strand, second_strand
    hamming_count = 0

    test_length = [first_strand.length, second_strand.length].min

    test_length.times do |i|
      if first_strand[i] != second_strand[i]
        hamming_count += 1
      end
    end

    hamming_count

  end
end
