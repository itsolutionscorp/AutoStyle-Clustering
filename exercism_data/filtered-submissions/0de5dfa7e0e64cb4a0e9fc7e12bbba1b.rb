class Hamming

  def compute(strand_1, strand_2)
    difference = 0
    strand_1 = strand_1.chars.take(strand_2.length)

    strand_1.each_with_index do |nucleotide, index|
      difference += 1 if nucleotide != strand_2[index]
    end
    return difference
  end

end
