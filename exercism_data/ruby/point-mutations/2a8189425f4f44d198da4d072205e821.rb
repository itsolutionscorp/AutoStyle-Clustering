class DNA

  def initialize strand
    @base_strand = strand
  end

  def hamming_distance comparison_strand
    shortest_strand_length(comparison_strand).times.count do |x|
      split_dna_strand(@base_strand)[x] != split_dna_strand(comparison_strand)[x]
    end
  end

private

  def shortest_strand_length comparison_strand
    compare_length = []
    compare_length.push(comparison_strand.length)
    compare_length.push(@base_strand.length)
    compare_length.min
  end

  def split_dna_strand strand
    strand.split('')
  end

end
