class Hamming
  def self.compute(strand1, strand2)
    strand1.chars.each.with_index.count do |char, index|
      strand2[index] && strand2[index] != char
    end
  end
end
