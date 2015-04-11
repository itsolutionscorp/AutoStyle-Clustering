class DNA
  THYAMINE = 'T'
  URACIL = 'U'

  def initialize(dna_string)
    @dna_string= dna_string 
  end

  def to_rna
    @dna_string.gsub(THYAMINE, URACIL)
  end
end
