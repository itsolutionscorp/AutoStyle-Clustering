class Hamming

  def compute(first_strand, second_strand)
    hamming_distance = 0
    first_strand = first_strand.split("")
    second_strand = second_strand.split("")

    length_of_strands = trim_strands_equal(first_strand, second_strand) - 1

    (0..length_of_strands).each do |f|
      if first_strand[f] != second_strand[f]
        hamming_distance += 1
      else
        hamming_distance
      end
    end

    hamming_distance
  end

  private

  def trim_strands_equal(first_strand, second_strand)
    first_length = first_strand.length
    second_length = second_strand.length

    if first_length > second_length
      trimmed_equal_length = first_strand.first(second_length).length
    elsif second_length > first_length
      trimmed_equal_length = second_strand.first(first_length).length
    else
      trimmed_equal_length = first_length
    end

    trimmed_equal_length
  end

end
