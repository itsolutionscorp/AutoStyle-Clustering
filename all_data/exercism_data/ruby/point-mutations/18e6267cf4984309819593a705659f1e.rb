class DNA

  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def hamming_distance(compared_strand)
    measure_mutations(strand.chars, compared_strand.chars)
  end

  private

  def measure_mutations(originals, potentials)
    mutations(originals, potentials).count
  end

  def mutations(originals, potentials)
    originals.zip(potentials).select do |pair|
      mutation?(pair)
    end
  end

  def mutation?(pair)
    pair.first != pair.last unless pair.last.nil?
  end

end
