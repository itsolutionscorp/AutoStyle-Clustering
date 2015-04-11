class Hamming
  def self.compute(strand1, strand2)
    short, long = align strand1, strand2
    short.zip(long).count { |a, b| a != b }
  end

  private

  def self.align(strand1, strand2)
    [strand1.chars, strand2.chars].sort_by(&:length)
  end
end
