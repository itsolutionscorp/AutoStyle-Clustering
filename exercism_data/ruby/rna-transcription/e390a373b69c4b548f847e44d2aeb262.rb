module Complement

  DNA_NUCLEOTIDES = 'GCTA'
  RNA_NUCLEOTIDES = 'CGAU'
  
  def self.of_dna(dna)
    if not_dna?(dna)
      raise ArgumentError,"#{dna} is not a valid dna sequence. Your
      sequence must only contain these nucleotides: #{DNA_NUCLEOTIDES}" 
    end
    dna.tr(DNA_NUCLEOTIDES,RNA_NUCLEOTIDES)
  end

  def self.not_dna?(dna)
    dna !~ /G|C|T|A/
  end

  def self.of_rna(rna)
    if not_rna?(rna) 
      raise ArgumentError,"#{rna} is not a valid rna sequence. Your
      sequence must only contain these nucleotides: #{RNA_NUCLEOTIDES}"
    end
    rna.tr(RNA_NUCLEOTIDES,DNA_NUCLEOTIDES)
  end

  def self.not_rna?(rna)
    rna !~ /C|G|A|U/
  end
end
