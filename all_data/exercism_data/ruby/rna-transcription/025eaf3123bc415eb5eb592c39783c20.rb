class Complement
  DNA_MAPPINGS = "GCTA"
  RNA_MAPPINGS = "CGAU"

  def self.of_dna(strand)
    strand.tr(DNA_MAPPINGS, RNA_MAPPINGS)
  end

  def self.of_rna(strand)
    strand.tr(RNA_MAPPINGS, DNA_MAPPINGS)
  end
end
