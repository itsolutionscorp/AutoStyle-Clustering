class DNA
  attr_reader :strand
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other_strand)
    shorter, longer = sort_by_length(strand, other_strand)
    distance = 0
    shorter.chars.each_with_index do |value, index|
      distance += 1 unless longer[index] == value
    end
    distance
  end

  private
  def sort_by_length(strand, other_strand)
    [strand, other_strand].sort_by(&:length)
  end
end
