class DNA
  THYMINE = 'T'
  URACIL = 'U'

  def initialize(dna)
    @dna = dna
  end

  def to_rna
    @dna.gsub(THYMINE, URACIL)
  end
end
