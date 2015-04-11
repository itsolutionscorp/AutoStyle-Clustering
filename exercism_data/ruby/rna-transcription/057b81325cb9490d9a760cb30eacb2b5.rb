class DNA
  THYMINE = "T"
  URACIL  = "U"

  # E.g. "GATTACA".
  def initialize(string)
    @string = string
  end

  # E.g. "GAUUACA".
  def to_rna
    @string.gsub(THYMINE, URACIL)
  end
end
