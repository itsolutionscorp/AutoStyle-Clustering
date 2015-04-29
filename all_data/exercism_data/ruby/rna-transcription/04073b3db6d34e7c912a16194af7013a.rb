class DNA
  THYMINE = 'T'
  URACIL  = 'U'
  
  def initialize(chain)
    @dna = chain
  end

  def to_rna
   @dna.tr(THYMINE, URACIL) 
  end
end
