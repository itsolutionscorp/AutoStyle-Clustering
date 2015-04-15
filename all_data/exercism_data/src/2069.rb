def compute(first_strand, second_strand)
    first_strand.chars.map.with_index { |character, index|
      character != second_strand[index]
    }.count { |diff| diff }
  end