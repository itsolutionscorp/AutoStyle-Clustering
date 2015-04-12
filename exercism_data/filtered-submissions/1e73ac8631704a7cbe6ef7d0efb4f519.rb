class Hamming

  def compute(first_strand, second_strand)
    distance = 0
    if first_strand.length <= second_strand.length
      strand1 = first_strand
      strand2 = second_strand
    else
      strand1 = second_strand
      strand2 = first_strand
    end
    strand1_nucleotides = strand1.split("")
    strand2_nucleotides = strand2.split("")
    strand1_nucleotides.each_with_index do |nucleotide, i|
      distance += 1 if nucleotide != strand2_nucleotides[i]
    end
    distance
  end

end
