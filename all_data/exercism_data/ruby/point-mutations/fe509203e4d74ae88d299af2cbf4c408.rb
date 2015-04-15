class DNA
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other_strand)
    if strand == other_strand
      distance = 0
    else
      strand.chars.take(other_strand.length).zip(other_strand.chars).select {|(a, b)| a != b}.count
    end
  end

  private

  attr_reader :strand
end
