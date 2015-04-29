class Hamming
  def self.compute(strandA, strandB)
    strandA.chars.zip(strandB.chars).reject { |a, b| b.nil? || a == b }.size
  end
end
