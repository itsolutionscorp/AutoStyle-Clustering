class DNA
  NUCLEOTIDES = 'ACGT'
  def initialize strand
    @strand = strand
  end

  def to_rna
    @strand.tr DNA::NUCLEOTIDES, RNA::NUCLEOTIDES
  end
end

class RNA
  NUCLEOTIDES = 'ACGU'
end
