def compute(a_strand, b_strand)
    differences = 0
    a_strand.chars.each_with_index do |element, index|
      differences += 1 if b_strand[index] && element != b_strand[index]
    end
    return differences
  end