class DNA
  ADENINE = "A"
  CYTOSINE = "C"
  GUANINE = "G"
  THYMINE = "T"
  URACIL = "U"

  def initialize(nucleotides)
    @nucleotides = nucleotides
  end

  def to_rna
    @nucleotides.gsub(THYMINE, URACIL)
  end
end
