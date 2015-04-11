class Complement
  NUCLEODITES = 'GCTA'
  COMPLEMENTS = 'CGAU'

  def self.of_dna(dna_strand)
    transcript(dna_strand, NUCLEODITES, COMPLEMENTS)
  end

  def self.of_rna(rna_strand)
    transcript(rna_strand, COMPLEMENTS, NUCLEODITES)
  end

  private

  def self.transcript(strand, from, to)
    raise ArgumentError unless strand.delete(from).empty?
    strand.tr(from, to)
  end
end
