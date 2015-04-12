class Hamming
  def compute(strand1, strand2)
    strand1 = strand1.chars.to_a
    strand2 = strand2.chars.to_a

    pairs = strand1.zip(strand2)
    pairs.count { |base1, base2| base1 != base2 }
  end
end
