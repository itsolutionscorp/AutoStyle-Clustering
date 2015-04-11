class DNA

  def initialize strand
    @base_strand = strand
  end

  def hamming_distance comparison_strand
    comparison = comparison_strand.chars.to_a
    base = @base_strand.chars.to_a
    shortest_strand_length(comparison_strand).times.count do |x|
      comparison[x] != base[x]
    end
  end

private

  def shortest_strand_length comparison_strand
    [comparison_strand.length, @base_strand.length].min
  end

end
