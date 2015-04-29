class Complement

  DNA_RNA = ['GCTA','CGAU']
  RNA_DNA = DNA_RNA.reverse

  def self.of_dna(dna_sequence)
    dna_sequence.tr(*DNA_RNA)
  end

  def self.of_rna(rna_sequence)
    rna_sequence.tr(*RNA_DNA)
  end

end
