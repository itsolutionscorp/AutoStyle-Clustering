def compute(first_strand, second_strand)
    second_strand.chars.each_with_object([]).with_index do |(letter, count), index|
      count << (second_strand[index] != first_strand[index])
    end.count(true)
  end