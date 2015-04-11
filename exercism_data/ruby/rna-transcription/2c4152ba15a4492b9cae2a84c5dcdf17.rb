class DNA
  def initialize(dna)
  	@nucleotides = dna
  end

  def to_rna
  	@nucleotides.gsub("T", "U")
  end
end
