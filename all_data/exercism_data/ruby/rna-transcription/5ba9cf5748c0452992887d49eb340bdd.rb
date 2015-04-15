class DNA < String
  
  URACIL    = 'U'
  THYMIDINE = 'T'
    
  def to_rna
    tr(THYMIDINE, URACIL)
  end
  
end
