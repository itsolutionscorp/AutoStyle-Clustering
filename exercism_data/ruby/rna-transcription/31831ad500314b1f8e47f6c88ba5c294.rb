class DNA
  ACIDS = "ACGT"

  def initialize(dna_chain)
    @chain = dna_chain.to_s
  end

  def to_rna
    @chain.tr(DNA::ACIDS, RNA::ACIDS)
  end
end

class RNA
  ACIDS = "ACGU"
end
