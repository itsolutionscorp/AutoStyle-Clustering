class Hamming
  def compute(strand1, strand2)
    pairs = strand1.length < strand2.length ? strand1.chars.zip(strand2.chars) : strand2.chars.zip(strand1.chars)
    pairs.count {|base1, base2| base1 != base2}
  end
end
