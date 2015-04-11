class Hamming
  def self.compute(strand_1, strand_2)
    strand_1.chars.zip(strand_2.chars).count { |one, another| one != another }
  end
end
