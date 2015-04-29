class DNA
  def initialize(dna_chain)
    @chain = dna_chain.to_s
  end

  def to_rna
    @chain.gsub("T","U")
  end
end
