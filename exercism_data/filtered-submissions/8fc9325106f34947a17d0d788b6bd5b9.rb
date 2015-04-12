class Hamming
  def compute(strand1, strand2)
    hamming = 0

    strand1.chars.zip(strand2.chars).each { |val| hamming += 1 unless val[0] == val[1] }
    hamming
  end
end
