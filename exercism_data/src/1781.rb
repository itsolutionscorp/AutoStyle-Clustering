class Hamming
  def compute(strand_1, strand_2)
    zipped_strands = strand_1.chars.zip(strand_2.chars)

    zipped_strands.inject(0) do |distance, (this_char, other_char)|
      this_char != other_char ? distance + 1 : distance
    end
  end
end
