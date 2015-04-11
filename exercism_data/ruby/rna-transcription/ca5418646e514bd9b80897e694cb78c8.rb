class DNA

  def initialize(dna_sequence)
    @dna_sequence = dna_sequence
  end

  def to_rna
    @dna_sequence.tr("T", "U")
  end
    
end
