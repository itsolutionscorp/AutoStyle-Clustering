class DNA < String
  THYMINE = 'T' unless defined? THYMINE
  URACIL  = 'U' unless defined? URACIL

  def to_rna
    gsub(THYMINE, URACIL)
  end
end
