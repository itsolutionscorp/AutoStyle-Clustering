def compute(a_strand, b_strand)
    differences = 0
    a_strand.split('').each_with_index do |element, index|
      if b_strand[index] && a_strand[index] != b_strand[index]
        differences += 1
      end
    end
    return differences
  end