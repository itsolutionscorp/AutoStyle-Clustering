class DNA 
  
  URACIL    = 'U'
  THYMIDINE = 'T'
  
  def initialize(strand)
    @strand = strand
  end
    
  def to_rna
    @strand.tr(THYMIDINE, URACIL)
  end
  
end
