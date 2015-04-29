class DNA

  THYMINE = 'tT'
  URACIL = 'uU'

  def initialize(stand)
    @stand = stand
  end

  def to_rna
    @stand.tr(THYMINE, URACIL)
  end

end
