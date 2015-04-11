class DNA
  def initialize(dna)
    @dna = dna.chars
  end

  def hamming_distance(other)
    original, other = align_with other.chars
    original.zip(other).select { |pair| pair[0] != pair[1] }.size
  end

  private

  def align_with(other)
    min = [@dna.size, other.size].min
    return @dna.take(min), other.take(min)
  end
end
