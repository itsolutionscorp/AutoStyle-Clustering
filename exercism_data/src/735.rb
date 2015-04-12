def compute(first_strand, second_strand)
    # length = first_strand.length
    #
    # (0..length).map do |index|
    #   (first_strand[index] <=> second_strand[index]).abs
    # end.reduce(:+)

    first_strand.chars.zip(second_strand.chars).count do |pair|
      pair[0] != pair[1]
    end
  end