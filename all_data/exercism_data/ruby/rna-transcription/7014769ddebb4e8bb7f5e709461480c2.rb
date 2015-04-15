class DNA
  def initialize(dna_string)
    @dna_string = dna_string
  end

  def to_rna
    thymine = 'T'
    uracil = 'U'
    @dna_string.tr(thymine, uracil)
  end
end
