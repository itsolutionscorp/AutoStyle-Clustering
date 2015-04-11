class DNA
  attr_reader :sequence

  def initialize(dna)
    @sequence = dna
  end

  def to_rna
    sequence.gsub("T", "U")
  end
end
