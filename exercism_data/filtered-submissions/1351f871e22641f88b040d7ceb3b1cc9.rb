class Hamming
  def compute(strand1, strand2)
    strand1.chars.zip(strand2.chars).count { |val| val[0] != val[1] }
  end
end
