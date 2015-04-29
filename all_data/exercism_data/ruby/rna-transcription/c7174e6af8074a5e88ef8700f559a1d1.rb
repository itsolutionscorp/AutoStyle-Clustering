class DNA
  def initialize(dna_chain)
    @dna = AcidChain.new(dna_chain)
  end

  def to_rna
    @dna.to_rna
  end
end

class AcidChain
  def initialize(chain)
    @chain = chain.to_s
  end

  def to_rna
    @chain.gsub("T","U")
  end
end
