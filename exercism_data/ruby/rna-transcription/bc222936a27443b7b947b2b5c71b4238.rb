class DNA < String
  URACIL  = 'U'
  THYMINE = 'T'

  def to_rna
    tr THYMINE, URACIL
  end
end
