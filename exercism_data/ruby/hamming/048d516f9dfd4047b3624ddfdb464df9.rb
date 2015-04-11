class DNA
  
  def initialize(dna)
    @nucleotides = dna.chars
  end
  
  def hamming_distance(other_dna)
    other_nucleotides = other_dna.chars
    homologous_nucleotides(other_nucleotides).count do |nucleotide, other_nucleotide|
      nucleotide != other_nucleotide
    end
  end
  
  private
  
  def homologous_nucleotides(other_nucleotides)
    shorter, longer = [@nucleotides, other_nucleotides].sort_by(&:length)
    shorter.zip(longer)
  end
  
end
