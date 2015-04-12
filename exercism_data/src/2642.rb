class Hamming
  def compute(strand_1, strand_2)
    zipped_strands = strand_1.chars.zip(strand_2.chars)

    zipped_strands.select do |this_char, other_char|
      this_char != other_char
    end.count
  end
end
