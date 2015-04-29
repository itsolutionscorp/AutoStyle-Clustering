class DNA

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other)
    pairs(@strand, other).count(&non_matching)
  end

  private

  def pairs(first, second)
    nucleotides(first, second.length).zip(nucleotides(second))
  end

  def nucleotides(strand, length = strand.length)
    strand.chars.to_a[0...length]
  end

  def non_matching
    lambda{|pair| pair[0] != pair[1]}
  end
end
