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
    originals.zip(potentials).select do |original, potential|
      mutation?(original, potential)
    end
  end

  def mutation?(original_dna, potential_mutation)
    original_dna != potential_mutation unless potential_mutation.nil?
  end

end
