class DNA < String
  
  # Transcribes DNA to RNA
  # by replacing Thymidine with Uracil
  def to_rna
    self.tr 'T', 'U'
  end
  
end
