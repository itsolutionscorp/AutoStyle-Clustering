class DNA
  THYAMINE = 'T'
  URACIL = 'U'

  def initialize(nucleotides)
    @nucleotides = nucleotides
  end

  def to_rna
    @nucleotides.gsub(THYAMINE, URACIL)
  end
end
