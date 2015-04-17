class Complement
  # Translate DNA to RNA and RNA to DNA.
  # * expands(splats) DNA_RNA_MAPPINGS for tr.
  DNA_RNA_MAPPINGS = ['GCTA', 'CGAU']

  def self.of_dna(strand)
    strand.tr(*DNA_RNA_MAPPINGS)
  end

  def self.of_rna(strand)
    strand.tr(*DNA_RNA_MAPPINGS.reverse)
  end
end