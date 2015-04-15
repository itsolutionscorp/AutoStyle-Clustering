class DNA

  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(second_strand)
    #non_matching_pairs(second_strand).reduce(0) {|sum,pair| sum + 1}
    non_matching_pairs(second_strand).length
  end

  def non_matching_pairs(second_strand)
    zipped_strands(second_strand).select{|pair| non_matching_pair?(pair)}
  end

  def non_matching_pair?(pair)
    pair[0] != pair[1] && pair[1]
  end

  def zipped_strands(second_strand)
    strand.split("").zip(second_strand.split(""))
  end

end
