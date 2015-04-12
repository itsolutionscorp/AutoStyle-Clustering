def compute(first_strand, second_strand)
    first_strand.chars.take(second_strand.length)
                .zip(second_strand.chars)
                .count {|first_strand_point, second_strand_point| first_strand_point != second_strand_point }
  end