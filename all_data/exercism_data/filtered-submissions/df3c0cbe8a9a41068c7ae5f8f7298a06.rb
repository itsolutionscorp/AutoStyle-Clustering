def compute(earlier_strand, later_strand)
    maximum_comparison_length = [earlier_strand.size, later_strand.size].min
    (0...maximum_comparison_length).select { |index|
      earlier_strand[index] != later_strand[index]
    }.count
  end