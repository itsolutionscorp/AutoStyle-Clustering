class DNA
  THYMINE = "T"
  URACIL = "U"

  def initialize(dna_string)
    @string = dna_string
  end

  def to_rna
    @string.gsub(THYMINE, URACIL)
  end
end
