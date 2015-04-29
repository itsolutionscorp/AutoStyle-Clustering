class Hamming
  def self.compute(strand1, strand2)
    strand1 = strand1.chars.to_a
    strand2 = strand2.chars.to_a

    pairs = strand1.zip(strand2)
    pairs.count { |pair| pair[0] != pair[1] }
  end
end
