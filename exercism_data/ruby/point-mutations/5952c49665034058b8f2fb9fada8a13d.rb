class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other_strand)
    strand_contents = [strand, other_strand].map(&:chars)
    shorter, longer = strand_contents.sort_by(&:length)
    shorter.zip(longer).count { |a, b| a != b }
  end
end
