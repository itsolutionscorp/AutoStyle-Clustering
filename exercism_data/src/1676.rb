def compute(first_strand, second_strand)

    second_strand_chars = second_strand.chars

    first_strand
      .chars
      .each_with_index
      .map {|char, index| char != second_strand.chars[index] }
      .select {|different| different }
      .count
  end