class Hamming
  def self.compute(strand1, strand2)
    paired_strands(strand1, strand2).count do |pair|
      pair.first != pair.last
    end
  end

  def self.paired_strands(strand1, strand2)
    strand1.chars.zip(strand2.chars)
  end
end
