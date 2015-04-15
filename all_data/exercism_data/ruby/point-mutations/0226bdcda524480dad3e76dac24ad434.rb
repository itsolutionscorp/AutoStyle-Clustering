class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(match_strand)
    shortest_length(strand, match_strand).times.count { |i| strand[i] != match_strand[i] }
  end

  private

  def shortest_length(strand1, strand2)
    [strand1.length, strand2.length].min
  end
end
