def compute(first_strand, second_strand)
    first_strand.chars.zip(second_strand.chars).count{ |x,y| x != y }
  end