class Hamming
  def self.compute(strand1, strand2)
    strand1.chars.count.with_index { |char, index| strand2[index] && strand2[index] != char }
  end
end
