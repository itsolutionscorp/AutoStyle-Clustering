class Complement
  NUCLEOTIDES = {dna: 'GCTA', rna: 'CGAU'}

  def self.of_dna(dna_strand)
    dna_strand.tr(NUCLEOTIDES[:dna], NUCLEOTIDES[:rna])
  end

  def self.of_rna(rna_strand)
    rna_strand.tr(NUCLEOTIDES[:rna], NUCLEOTIDES[:dna])
  end
end
