class DNA
  def initialize(dna)
    @dna = dna.chars
  end

  def hamming_distance(other)
    original, other = align_with other
    original.each_with_index.inject(0) do |total, (acid, i)|
      total += 1 unless acid ==other[i]; total
    end
  end

  private

  def align_with(other)
    min = [@dna.size, other.size].min
    return @dna[0...min], other[0...min]
  end
end
