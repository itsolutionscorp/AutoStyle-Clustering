class Hamming
  def self.compute(strandA, strandB)
    positional_pairs(strandA, strandB).reject { |a, b| a == b }.size
  end

  private

  def self.positional_pairs(strandA, strandB)
    strandA.chars.zip(strandB.chars).reject { |_, b| b.nil? }
  end
end
