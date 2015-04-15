class Hamming
  def self.compute(strand1, strand2)
    strand1.chars.zip(strand2.chars).count {|pair| pair[0] != pair[1]}
  end
end
