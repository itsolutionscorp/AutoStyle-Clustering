class DNA
  attr_reader :strand
  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(new_strand)
    pair_with(new_strand).count { |pair| mutated?(pair) }
  end

  private
  def pair_with(other)
    length_to_compare = [strand.length, other.length].min
    nucleotides(strand, length_to_compare).zip(
      nucleotides(other, length_to_compare))
  end

  def mutated?(pair)
    pair.first != pair.last
  end

  def nucleotides(strand, length)
    strand.chars.take(length)
  end
end
