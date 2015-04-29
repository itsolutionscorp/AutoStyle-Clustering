class Complement

  DNA_NUCLEOTIDES = 'GCTA'
  RNA_NUCLEOTIDES = 'CGAU'
  
  def self.of_dna(dna)
    if dna !~ /G|C|T|A/
      raise ArgumentError,"That is not a correct dna sequence"
    end
    dna.tr(DNA_NUCLEOTIDES,RNA_NUCLEOTIDES)
  end

  def self.of_rna(rna)
    if rna !~ /C|G|A|U/
      raise ArgumentError,"That is not a correct rna sequence"
    end
    rna.tr(RNA_NUCLEOTIDES,DNA_NUCLEOTIDES)
  end
end
