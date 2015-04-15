class DNA < String
  THYMINE = 'T'
  URACIL = 'U'

  def to_rna
    gsub(THYMINE, URACIL)
  end
end
