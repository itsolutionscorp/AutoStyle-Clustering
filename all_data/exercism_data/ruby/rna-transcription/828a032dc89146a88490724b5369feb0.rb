class DNA 
  THYMIDINE = 'T'
  URACIL = 'U'

  def initialize(dna)
    @dna = dna
  end

  def to_rna
    @dna.gsub(THYMIDINE, URACIL)
  end
end
