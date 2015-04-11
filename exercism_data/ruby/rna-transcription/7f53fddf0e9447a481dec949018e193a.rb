class DNA
  THYMINE = 'T'
  URACIL = 'U'

  def initialize(string)
    @string = string
  end

  def to_rna
    @string.tr(THYMINE, URACIL)
  end
end
