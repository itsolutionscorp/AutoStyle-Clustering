class DNA

  def initialize strand
    @base_strand = strand
  end

  def hamming_distance comparison_strand
    shortest_strand_length(comparison_strand).times.count do |x|
      comparison_strand[x] != @base_strand[x]
    end
  end

private

  def shortest_strand_length comparison_strand
    [comparison_strand.length, @base_strand.length].min
  end

end
