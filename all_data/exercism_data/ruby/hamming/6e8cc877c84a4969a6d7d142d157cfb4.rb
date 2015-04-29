class Hamming
  def self.compute(strand1, strand2)
    pairs = stepwise_combined_nucleotides(strand1, strand2)
    pairs.count { |pair| pair[1] && pair[0] != pair[1] }
  end

  private

  def self.stepwise_combined_nucleotides(strand1, strand2)
    strand1_nucleotides = strand1.chars
    strand2_nucleotides = strand2.chars
    strand1_nucleotides.zip(strand2_nucleotides)
  end
end
