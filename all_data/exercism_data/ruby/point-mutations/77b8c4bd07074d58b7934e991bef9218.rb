class DNA
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(descendant_strand)
    return 0 if descendant_strand == @strand
    difference = 0
    @strand.chars.each_with_index do |nucleotide, idx|
      next if descendant_strand[idx].nil?
      difference += 1 if nucleotide != descendant_strand[idx]
    end
    difference
  end
end
