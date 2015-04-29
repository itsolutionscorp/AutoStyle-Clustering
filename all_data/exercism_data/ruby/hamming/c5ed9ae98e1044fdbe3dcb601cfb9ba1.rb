class DNA
  
  def initialize(dna_1)
    @nucleotides_1 = dna_1.chars
  end
  
  def hamming_distance(dna_2)
    @nucleotides_2 = dna_2.chars
    homologous_nucleotides.count do |nucleotide_1, nucleotide_2|
      nucleotide_1 != nucleotide_2
    end
  end
  
  private
  
  def homologous_nucleotides
    shorter, longer = [@nucleotides_1, @nucleotides_2].sort_by(&:length)
    shorter.zip(longer)
  end
  
end
