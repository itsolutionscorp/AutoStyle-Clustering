class DNA
  def initialize(strand)
    @strand = strand.chars
  end

  def hamming_distance(strand)
    @strand.zip_with(strand.chars).count { |a, b| a != b }
  end
end

module Enumerable
  def zip_with(secondary)
    self.take(secondary.size).zip secondary
  end
end
