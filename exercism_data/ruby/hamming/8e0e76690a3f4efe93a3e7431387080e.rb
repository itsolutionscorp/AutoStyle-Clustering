class DNA

  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other_strand)
    original, other = strand.chars, other_strand.chars
    length  = [original.length, other.length].min
    results = original[0, length].zip(other[0, length])
    results.select{|i| i.first != i.last }.length
  end
end
