class Hamming
  def self.compute(strand1, strand2)
    common_length = [strand1.size, strand2.size].min
    nuclides1 = strand1[0, common_length].chars
    nuclides2 = strand2[0, common_length].chars

    nuclides1.zip(nuclides2).count { |pair| pair[0] != pair[1] }
  end
end
