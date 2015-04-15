class DNA

  THYMIDINE = "T"
  URACIL = "U"
  
  def initialize(sequence)
    @sequence = sequence
  end

  def to_rna
    @sequence.tr(THYMIDINE, URACIL)
  end
    
end
