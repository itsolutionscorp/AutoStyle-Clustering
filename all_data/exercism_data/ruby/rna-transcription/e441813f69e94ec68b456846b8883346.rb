class DNA
  def initialize(nucleotides)
    @dna_nucleotides = nucleotides
  end
  
  attr_reader   :dna_nucleotides

  def to_rna
    dna_nucleotides.gsub(/[T]/, 'U')
  end
end
