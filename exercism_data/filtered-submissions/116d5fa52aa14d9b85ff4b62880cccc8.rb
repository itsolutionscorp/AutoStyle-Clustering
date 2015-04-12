class Hamming
  def compute(strand1, strand2)
    strand1.chars.zip(strand2.chars).count { |base1, base2| base1 != base2 }
  end
end
