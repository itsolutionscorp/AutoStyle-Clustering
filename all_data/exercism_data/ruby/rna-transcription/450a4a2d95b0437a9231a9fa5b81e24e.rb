class DNA
  THYMINE = 'T'
  URACIL = 'U'
  
  def initialize(dna_string)
    @dna_string = dna_string
  end

  def to_rna
    @dna_string.tr(THYMINE, URACIL)
  end
end
