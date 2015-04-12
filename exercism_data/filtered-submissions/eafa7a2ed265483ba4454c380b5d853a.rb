class Hamming

  def compute(a_strand, other_strand)
    repetition = 0
    other_strand = other_strand.chars

    a_strand.chars.each_with_index do |c, i|
      repetition += 1 if other_strand.at(i) != c
    end

    repetition
  end

end
