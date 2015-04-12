def compute(first_strand, second_strand)
    first_strand.chars.each_with_index.map do |char, index|
      first_strand[index] == second_strand[index]
    end.count(false)
  end