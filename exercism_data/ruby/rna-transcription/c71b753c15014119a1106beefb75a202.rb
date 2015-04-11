class DNA < String
  
  URACIL    = 'U'
  THYMIDINE = 'T'
    
  def to_rna
    self.gsub(THYMIDINE, URACIL)
  end
  

end
