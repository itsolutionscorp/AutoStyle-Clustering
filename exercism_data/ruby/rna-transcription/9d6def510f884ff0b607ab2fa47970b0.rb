class Complement
  NUCLEODITES = 'GCTA'
  COMPLEMENTS = 'CGAU'

  def self.of_dna(dna_strand)
    dna_strand.tr(NUCLEODITES, COMPLEMENTS)
  end

  def self.of_rna(rna_strand)
    rna_strand.tr(COMPLEMENTS, NUCLEODITES)
  end
end
