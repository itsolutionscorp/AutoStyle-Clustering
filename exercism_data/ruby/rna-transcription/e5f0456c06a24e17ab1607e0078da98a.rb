class DNA
  attr_reader :nucleotides

  THYMINE = 'T'
  URACIL  = 'U'

  def initialize(nucleotides)
    @nucleotides = nucleotides
  end

  def to_rna
    nucleotides.tr THYMINE, URACIL
  end
end
