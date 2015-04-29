class DNA
  GUANINE  = "G"
  ADENINE  = "A"
  THYMINE  = "T"
  CYTOSINE = "C"
  URACIL   = "U"

  def initialize(chain)
    @chain = chain
  end

  def to_rna
    @chain.gsub(THYMINE, URACIL)
  end
end
