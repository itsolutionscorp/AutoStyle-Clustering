class DNA
  def initialize(dna)
    @dna = dna
    @distance = 0
  end

  def hamming_distance(a_dna)
    @distance = 0
    i = 0
    while i < a_dna.length && i < @dna.length do
      @distance += 1 if a_dna[i] != @dna[i]
      i += 1
    end
    return @distance
  end
end
