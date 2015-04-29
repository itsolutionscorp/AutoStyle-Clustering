class DNA

  THYMIDINE = "T"
  URACIL = "U"

  def initialize(dna_string)
    @dna_string = dna_string
  end

  attr_reader :dna_string

  def to_rna
    dna_string.tr(THYMIDINE, URACIL)
  end

end
