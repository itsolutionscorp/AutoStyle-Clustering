module Complement
  DNA_TO_RNA = ['GCAT', 'CGUA']
  RNA_TO_DNA = DNA_TO_RNA.reverse

  def self.of_dna strand
    strand.tr(*DNA_TO_RNA)
  end

  def self.of_rna strand
    strand.tr(*RNA_TO_DNA)
  end
end
