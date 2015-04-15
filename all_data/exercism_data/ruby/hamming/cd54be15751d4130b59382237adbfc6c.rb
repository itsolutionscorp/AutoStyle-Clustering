class Hamming
  def self.compute strand1, strand2
    strand1 = Strand.new strand1
    strand2 = Strand.new strand2
    hamming_distance = 0

    self.comparable_length(strand1, strand2).times {|n| hamming_distance += 1 unless strand1.parts[n] == strand2.parts[n]}
    hamming_distance
  end
  def self.comparable_length strand1, strand2
    [strand1.parts.size, strand2.parts.size].min
  end
end

class Strand
  attr_reader :parts
  def initialize strand
    @parts = split_to_parts strand
  end
  def split_to_parts strand
    strand.chars.to_a
  end
end
