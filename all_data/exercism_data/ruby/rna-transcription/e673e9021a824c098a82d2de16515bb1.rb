class DNA < String
  THYMIDINE = 'T'
  URIDINE = 'U'

  def to_rna
    gsub(THYMIDINE, URIDINE)
  end
end
