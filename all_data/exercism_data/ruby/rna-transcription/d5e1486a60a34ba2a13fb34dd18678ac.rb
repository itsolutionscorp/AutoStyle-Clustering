module Complement

  DNA_NUCLEOTIDES = 'GCTA'
  RNA_NUCLEOTIDES = 'CGAU'
  
  def self.of_dna(dna)
    raise ArgumentError,"That is not a correct dna sequence." if not_dna?(dna)
    dna.tr(DNA_NUCLEOTIDES,RNA_NUCLEOTIDES)
  end

  def self.not_dna?(dna)
    dna !~ /G|C|T|A/
  end

  def self.of_rna(rna)
    raise ArgumentError,"That is not a correct rna sequence." if not_rna?(rna) 
    rna.tr(RNA_NUCLEOTIDES,DNA_NUCLEOTIDES)
  end

  def self.not_rna?(rna)
    rna !~ /C|G|A|U/
  end
end
