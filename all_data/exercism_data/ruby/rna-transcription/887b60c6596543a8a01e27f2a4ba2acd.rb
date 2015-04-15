class Complement

  DNA_RNA_MAPPINGS = ['GCTA', 'CGAU']

  # * expands DNA_RNA_MAPPINGS into a hash
  def self.of_dna(strand)
    strand.tr(*DNA_RNA_MAPPINGS)
  end

  def self.of_rna(strand)
    strand.tr(*DNA_RNA_MAPPINGS.reverse)
  end
end
