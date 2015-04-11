class DNA < String
  THYMINE = 'T'
  URACIL = 'U'

  def to_rna
    tr THYMINE, URACIL
  end
end
