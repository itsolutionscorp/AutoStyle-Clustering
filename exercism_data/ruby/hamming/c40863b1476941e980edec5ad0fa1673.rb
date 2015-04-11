class DNA
  def initialize(strand)
    @strand = strand.chars
  end

  def hamming_distance(strand)
    @strand.take_zipped(strand.chars).count { |a, b| a != b }
  end
end

module Enumerable
  def take_zipped(secondary)
    self.take(secondary.size).zip secondary
  end
end
