DNA = Struct.new(:sequence) do

  THYMIDINE = "T"
  URACIL = "U"
  
  def to_rna
    sequence.tr(THYMIDINE, URACIL)
  end
    
end
