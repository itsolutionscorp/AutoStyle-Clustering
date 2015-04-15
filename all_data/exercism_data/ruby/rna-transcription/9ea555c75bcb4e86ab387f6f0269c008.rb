class DNA

  GUANINE  = "G"
  ADENINE  = "A"
  THYMINE  = "T"
  CYTOSINE = "C"
  URACIL   = "U"

  attr_reader :dna_string
  def initialize(dna_string)
    @dna_string = dna_string
  end

  def to_rna
    dna_string.gsub(THYMINE, URACIL)
  end
end
