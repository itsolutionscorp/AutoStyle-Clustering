class DNA
  THYMINE = 'T'
  URACIL = 'U'

  def initialize(dna)
    @dna = dna
  end

  def to_rna
    @dna.tr(THYMINE, URACIL)
  end
end
