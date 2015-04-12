class Hamming
  def compute(strand_1, strand_2)
    strand_1.chars.zip(strand_2.chars).select { |c| c.first != c.last }.size
  end
end
