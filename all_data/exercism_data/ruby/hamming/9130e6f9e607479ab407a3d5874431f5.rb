class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other_strand)
    range_to_compare(other_strand).count do |i|
      strand[i] != other_strand[i]
    end
  end

  def range_to_compare(other_strand)
    smaller_size = [other_strand.size, strand.size].min
    0...smaller_size
  end
end
