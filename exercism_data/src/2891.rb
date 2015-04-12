class Hamming
  def compute(strand1, strand2)
    strand1.chars.zip(strand2.chars).count { |char1, char2| char1 != char2 }
  end
end
