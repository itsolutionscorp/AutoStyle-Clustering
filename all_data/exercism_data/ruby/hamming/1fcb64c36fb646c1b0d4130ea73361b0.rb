class DNA
  def initialize(strand)
    @strand = strand.chars
  end

  def hamming_distance(strand)
    @strand.take_zipped(strand.chars).count { |a, b| a != b }
  end
end

module Enumerable
  def equalise_length(secondary)
    # return an array of the size of the smaller array
    self.take secondary.size
  end

  def take_zipped(secondary)
    self.equalise_length(secondary).zip secondary
  end
end
