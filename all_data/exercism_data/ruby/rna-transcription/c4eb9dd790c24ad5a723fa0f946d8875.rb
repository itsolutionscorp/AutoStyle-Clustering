class DNA

  THYMINE = 'tT'
  URACIL = 'uU'

  def initialize(dna)
    @dna = dna
  end

  def to_rna
    @dna.tr(THYMINE, URACIL)
  end

end
