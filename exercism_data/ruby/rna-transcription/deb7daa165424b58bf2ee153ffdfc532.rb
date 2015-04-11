class DNA

  THYMINE = "T"
  URACIL = "U"


  def initialize(dna_sequence)
    @dna_sequence = dna_sequence
  end

  def to_rna
    @dna_sequence.tr(THYMINE, URACIL)
  end
end
