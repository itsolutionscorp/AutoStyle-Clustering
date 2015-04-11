class DNA

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(other)
    mismatched_pairs(@strand, other).count
  end

  private

  def mismatched_pairs(strand1, strand2)
    nucleotide_pairs(strand1, strand2).select do |pair|
      pair[0] != pair[1]
    end
  end

  def nucleotide_pairs(strand1, strand2)
    corresponding_nucleotides(strand1, strand2).reject do |pair|
      pair[0].nil? || pair[1].nil?
    end
  end

  def corresponding_nucleotides(strand1, strand2)
    strand1.chars.zip(strand2.chars)
  end

end
