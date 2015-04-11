class Complement
  DNA_TO_RNA_MAPPINGS = ['GCTA', 'CGAU']
  RNA_TO_DNA_MAPPINGS = DNA_TO_RNA_MAPPINGS.reverse

  def self.of_dna(dna_strand)
    dna_strand.tr(*DNA_TO_RNA_MAPPINGS)
  end

  def self.of_rna(rna_strand)
    rna_strand.tr(*RNA_TO_DNA_MAPPINGS)
  end
end
