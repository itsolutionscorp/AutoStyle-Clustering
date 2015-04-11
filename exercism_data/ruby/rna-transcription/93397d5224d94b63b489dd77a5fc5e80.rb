class DNA
  def initialize(nucleotide_chain)
    @chain = nucleotide_chain
  end

  def to_rna
    @chain.gsub('T', 'U')
  end
end
