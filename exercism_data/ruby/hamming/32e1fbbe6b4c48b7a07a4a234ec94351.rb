class DNA
  def initialize strand
    @strand = strand.upcase
  end

  def hamming_distance other_strand
    count = 0

    @strand.chars.each_with_index do |nucleotide, index|
      break if other_strand[index].nil?
      count += 1 if nucleotide != other_strand[index]
    end

    count
  end
end
