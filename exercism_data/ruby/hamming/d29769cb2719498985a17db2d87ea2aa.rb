class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other_strand)
    range_to_compare(other_strand).inject(0) do |distance, i|
      distance + (strand[i] != other_strand[i] ? 1 : 0)
    end
  end

  def range_to_compare(other_strand)
    smaller_length = [other_strand.length, strand.length].min

    (0...smaller_length)
  end
end
