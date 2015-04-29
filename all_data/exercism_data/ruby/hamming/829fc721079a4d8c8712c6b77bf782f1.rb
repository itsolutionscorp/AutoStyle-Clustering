class Hamming
  def self.compute strand1, strand2
    strand1 = Strand.new strand1
    strand2 = Strand.new strand2
    self.comparable_length(strand1, strand2).times.count{|n| strand1.parts[n] != strand2.parts[n]}
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
