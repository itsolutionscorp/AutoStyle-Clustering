class NucleicAcid
  NUCLEOTIDES = ''

  def self.nucleotides
    const_get 'NUCLEOTIDES'
  end

  def initialize strand
    @strand = strand
  end

  def to_str
    @strand
  end
end

class DNA < NucleicAcid
  NUCLEOTIDES = 'ACGT'

  def to_rna
    @strand.tr DNA.nucleotides, RNA.nucleotides
  end
end

class RNA < NucleicAcid
  NUCLEOTIDES = 'ACGU'

  def to_dna
    @strand.tr RNA.nucleotides, DNA.nucleotides
  end
end
