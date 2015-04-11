class DNA < String
  THYMIDINE = 'T'
  URIDINE = 'U'
  def to_rna
    self.gsub(THYMIDINE, URIDINE)
  end
end
