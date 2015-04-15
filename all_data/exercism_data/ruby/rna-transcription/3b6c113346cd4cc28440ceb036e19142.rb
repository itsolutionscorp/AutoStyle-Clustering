class Complement
  DNA_TO_RNA = ['GCTA', 'CGAU']
  RNA_TO_DNA = DNA_TO_RNA.reverse

  def self.of_dna(dna)
    dna.tr(*DNA_TO_RNA)
  end

  def self.of_rna(rna)
    rna.tr(*RNA_TO_DNA)
  end
end
