class Hamming
  def self.compute strand1, strand2
    strand1.slice(0, strand2.length).each_char
      .zip(strand2.each_char)
      .count { |pair| pair[0] != pair[1] }
  end
end
