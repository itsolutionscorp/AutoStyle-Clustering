class Complement

  NUCLEOTIDES = {
    dna: 'GCTA',
    rna: 'CGAU'
  }

  def self.of_dna dna_sequence
    complement dna_sequence, :dna, :rna
  end

  def self.of_rna rna_sequence
    complement rna_sequence, :rna, :dna
  end

  def self.complement sequence, from, to
    sequence.tr NUCLEOTIDES[from], NUCLEOTIDES[to]
  end

end
