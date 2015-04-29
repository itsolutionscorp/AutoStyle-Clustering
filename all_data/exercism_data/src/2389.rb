def compute(first_strand, second_strand)
    differences = 0
    first_strand.chars.zip(second_strand.chars) { |x, y| differences += 1 if x != y }
    differences
  end