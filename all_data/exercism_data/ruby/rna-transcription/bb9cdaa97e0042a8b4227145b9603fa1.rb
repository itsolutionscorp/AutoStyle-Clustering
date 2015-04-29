class Complement

  RNA_COMPLEMENTS = "GCTA" 
  DNA_COMPLEMENTS = "CGAU"

  def self.find_complements(type, strand)
    if type == :rna
      strand.tr(DNA_COMPLEMENTS, RNA_COMPLEMENTS)
    else
      strand.tr(RNA_COMPLEMENTS, DNA_COMPLEMENTS)
    end
  end

  def self.of_dna(strand)
    find_complements(:dna, strand)
  end

  def self.of_rna(strand)
    find_complements(:rna, strand)
  end
end
