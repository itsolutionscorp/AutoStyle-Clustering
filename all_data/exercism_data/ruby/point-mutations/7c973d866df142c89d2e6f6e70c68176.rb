class DNA

  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(strand2)
    compare = @strand.chars.zip(strand2.chars)
    differences = compare.select {|elem| elem[0] != elem[1] && elem[0] != nil && elem[1] != nil}
    differences.length
  end

end
