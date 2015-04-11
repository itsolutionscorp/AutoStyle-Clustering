class Hamming
  def self.compute(strand1, strand2)
    new(strand1, strand2).compute
  end

  attr_reader :strand1, :strand2

  def initialize(strand1, strand2)
    @strand1 = strand1.chars
    @strand2 = strand2.chars
  end

  def compute
    shorter_strand, longer_strand = ordered_strands
    shorter_strand.zip(longer_strand).select do |nucleotide1, nucleotide2|
      nucleotide1 != nucleotide2
    end.count
  end

  private

  def ordered_strands
    if strand1.size > strand2.size
      [strand2, strand1]
    else
      [strand1, strand2]
    end
  end
end
