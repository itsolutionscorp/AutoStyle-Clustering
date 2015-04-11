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

  private

  attr_reader :strand
end

class HomologousDnaPair
  def initialize(first_strand, second_strand)
    @first_strand = first_strand
    @second_strand = second_strand
  end

  def hamming_distance
    nucleotide_pairs.select {|(first, second)| first != second}.count
  end

  private

  attr_reader :first_strand, :second_strand

  def nucleotide_pairs
    first_strand.nucleotides.take(length_of(second_strand)).zip(second_strand.nucleotides)
  end

  def length_of(strand)
    strand.nucleotides.length
  end
end
