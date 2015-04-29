class Hamming
  def self.compute(strand_1, strand_2)
    return nil if strand_1.length != strand_2.length
    strand_1.chars.zip(strand_2.chars).count { |c| c.first != c.last }
  end
end
