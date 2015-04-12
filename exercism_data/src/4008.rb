def compute(strand, compare_to_strand)
    units_of_strand = strand.chars.take(compare_to_strand.length)
    units_of_compare_to_strand = compare_to_strand.chars

    pairs_to_compare = units_of_strand.zip(units_of_compare_to_strand)

    pairs_to_compare.count do |left, right|
      left != right
    end
  end