class Complement

  DNA_TO_RNA_MAPPING = ["GCTA", "CGAU"]
  RNA_TO_DNA_MAPPING = DNA_TO_RNA_MAPPING.reverse

  def self.of_dna(dna_strand)
    dna_strand.tr(*DNA_TO_RNA_MAPPING)
  end

  def self.of_rna(rna_strand)
    rna_strand.tr(*RNA_TO_DNA_MAPPING)
  end

end
