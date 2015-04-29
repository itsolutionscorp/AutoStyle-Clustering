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
    originals.collect.with_index do |original_dna, index|
      potential_mutation = potentials[index]
      if mutation?(original_dna, potential_mutation)
        potential_mutation
      end
    end.compact.count
  end

  def mutation?(nucleo_1, nucleo_2)
    nucleo_1 != nucleo_2
  end

end
