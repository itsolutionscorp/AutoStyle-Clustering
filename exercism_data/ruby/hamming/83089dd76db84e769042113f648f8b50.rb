class Hamming < Struct.new(:strand1, :strand2)
  def self.compute(strand1, strand2)
    new(strand1, strand2).count_differences
  end

  def count_differences
    paired_characters.count { |char1, char2| char1 != char2 }
  end

protected

  def paired_characters
    strand1.chars.zip(strand2.chars)
  end
end
