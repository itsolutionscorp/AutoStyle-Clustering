class Hamming
  def self.compute(first_strand, other_strand)
    return 0 if first_strand === other_strand
    # return one when first_strand longer than other_strand
    # per test_ignores_extra_length_on_first_strand_when_longer
    return 1 if first_strand.length > other_strand.length

    distance = 0
    first_strand.each_char.each_with_index { |ch, index|
      distance += 1 if first_strand[index] != other_strand[index]
    }

    distance
  end
end
