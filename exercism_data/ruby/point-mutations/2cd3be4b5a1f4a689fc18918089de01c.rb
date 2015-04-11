class DNA

  def initialize strand
    @base_strand = strand
  end

  def hamming_distance comparison_strand
    count = 0
    shortest_strand_length(comparison_strand).times do |x|
      if split_dna_strand(@base_strand)[x] != split_dna_strand(comparison_strand)[x]
        count += 1
      end
    end
    count
  end

private

  def shortest_strand_length comparison_strand
    if comparison_strand.length <= @base_strand.length
      comparison_strand.length
    else
      @base_strand.length
    end
  end

  def split_dna_strand strand
    strand.split('')
  end

end
