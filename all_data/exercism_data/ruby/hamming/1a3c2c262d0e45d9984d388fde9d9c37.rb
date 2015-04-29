class Hamming

  def self.compute(first_strand, second_strand)
    assert_equal = 0

    first_strand.length.times do |count|
      if first_strand[count] != second_strand[count]
        if (first_strand[count] && second_strand[count]) != nil
          assert_equal += 1
        end
      end
    end
    assert_equal
  end

end
