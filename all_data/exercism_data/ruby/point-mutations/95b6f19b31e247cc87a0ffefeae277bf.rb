class DNA
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other_strand)
    HomologousDnaPair.new(self, DNA.new(other_strand)).hamming_distance
  end

  def nucleotides
    strand.chars
  end

  def length
    strand.length
  end

  private

  attr_reader :strand
end

class HomologousDnaPair
  def initialize(left, right)
    @left = left
    @right = right
  end

  def hamming_distance
    nucleotide_pairs.count {|(first, second)| first != second}
  end

  private

  attr_reader :left, :right

  def nucleotide_pairs
    left.nucleotides.take(right.length).zip(right.nucleotides)
  end
end
