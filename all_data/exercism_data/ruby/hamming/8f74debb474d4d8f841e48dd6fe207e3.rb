class Hamming
  def self.compute(strand1, strand2)
    return unless strand1.length == strand2.length
    strand1.chars.zip(strand2.chars).count do |symbol1, symbol2|
      symbol1 != symbol2
    end
  end
end
