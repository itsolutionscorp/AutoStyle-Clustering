class DNA
  def initialize(dna_chain)
    @dna_chain = dna_chain
  end

  def to_rna
    @dna_chain.gsub('T', 'U')
  end
end
