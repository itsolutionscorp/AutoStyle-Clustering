class Hamming

  def compute(first_nucleotide_strand, second_nucleotide_strand)
    first_set = first_nucleotide_strand.chars
    second_set = second_nucleotide_strand.chars

    difference_count = 0
    strand_position = 0

    first_set.each do |nucleotide_to_compare|
      if nucleotide_to_compare != second_set[strand_position]
        difference_count += 1 unless second_set[strand_position] == nil
      end
      strand_position += 1
    end

    difference_count
  end

end
