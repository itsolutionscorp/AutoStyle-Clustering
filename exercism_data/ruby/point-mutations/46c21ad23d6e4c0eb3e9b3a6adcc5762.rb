class DNA
  def initialize(dna)
    @dna = dna
  end

  def hamming_distance(other)
    original, other = align_with other
    original.zip(other).select { |pair| pair[0] != pair[1] }.size
  end

  private

  def align_with(other)
    min = [@dna.size, other.size].min
    return @dna.chars.take(min), other.chars.take(min)
  end
end
