class DNA
  def initialize(dna_chain)
    @dna = dna_chain.to_s
  end

  def to_rna
   @dna.tr('T', 'U') 
  end
end
