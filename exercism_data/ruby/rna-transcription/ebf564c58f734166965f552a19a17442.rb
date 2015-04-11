class DNA < String
  THYMIDINE = 'T'
  URACIL = 'U'

  def to_rna
    self.gsub(THYMIDINE, URACIL)
  end
end
