class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other_strand)
    stand_contents = [strand_chars, other_strand.chars]
    shorter, longer = stand_contents.sort_by &:length
    shorter.zip(longer).count { |a, b| a != b }
  end

  private
  def strand_chars
    @strand_chars ||= strand.chars
  end
end
