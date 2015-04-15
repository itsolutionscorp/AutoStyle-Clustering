class DNA

  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(second_strand)
    non_matching_pairs(second_strand).length
  end

  def non_matching_pairs(second_strand)
    equivalanet_pairs(second_strand).select{|a,b| a != b && b}
  end

  def equivalanet_pairs(second_strand)
    strand.split("").zip(second_strand.split(""))
  end

end
