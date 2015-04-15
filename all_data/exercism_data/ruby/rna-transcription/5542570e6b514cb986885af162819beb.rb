class DNA

  THYMIDINE = "T"

  def initialize(sequence)
    @sequence = sequence
  end

  def to_rna
    uracil = "U"
    @sequence.tr(THYMIDINE, uracil)
  end
    
end
