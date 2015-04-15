class DNA

  attr_reader :dna_string

  def initialize(dna_string)
    @dna_string = dna_string
  end

  def to_rna
    dna_string.tr("T", "U")
  end

end 
