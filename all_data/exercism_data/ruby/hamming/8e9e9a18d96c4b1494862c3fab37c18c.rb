class Hamming
  def self.compute(first_strand, second_strand)
    hamming_distance = 0
    first_strand = first_strand.chars
    second_strand = second_strand.chars
    while (!first_strand.empty?) && (!second_strand.empty?)
      first_strand_letter = first_strand.shift
      second_strand_letter = second_strand.shift
      if first_strand_letter != second_strand_letter
        hamming_distance += 1
      end
    end
    return hamming_distance
  end
end
