class Complement

  RNA_COMPLEMENTS = "GCTA" 
  DNA_COMPLEMENTS = "CGAU"
  
  def self.of_dna(strand)
    strand.tr(RNA_COMPLEMENTS, DNA_COMPLEMENTS)
  end

  def self.of_rna(strand)
    strand.tr(DNA_COMPLEMENTS, RNA_COMPLEMENTS)
  end
end
