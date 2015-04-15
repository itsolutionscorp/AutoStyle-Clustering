module Hamming
  def self.compute(*strands)
    mismatched_nucleotides(strands).count
  end

  private

  def self.mismatched_nucleotides(strands)
    aligned_nucleotides(strands).map(&:uniq).reject(&:one?)
  end

  def self.aligned_nucleotides(strands)
    strands.map(&:chars).transpose
  end
end
