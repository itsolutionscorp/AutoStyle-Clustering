class DNA
  ADENINE = "a"
  CYTOSINE = "c"
  GUANINE = "g"
  THYMINE = "t"
  URACIL = "u"

  def initialize(nucleotides)
    @nucleotides = nucleotides
  end

  def to_rna
    @nucleotides.gsub(THYMINE, URACIL)
  end
end
