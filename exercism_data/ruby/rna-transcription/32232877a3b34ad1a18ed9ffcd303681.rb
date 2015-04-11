class Complement

  DNA_nucleotides = "GCTA"
  RNA_nucleotides = "CGAU"

  def self.of_dna(dna)
    dna.tr(DNA_nucleotides, RNA_nucleotides)
  end

  def self.of_rna(rna)
    rna.tr(RNA_nucleotides, DNA_nucleotides)
  end
end
