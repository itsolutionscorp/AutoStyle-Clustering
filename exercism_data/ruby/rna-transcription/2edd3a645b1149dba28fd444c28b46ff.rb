class Complement

  DNA_TO_RNA = ['GCTA', 'CGAU']

  def self.of_dna(dna_to_transcribe)
    dna_to_transcribe.tr(*DNA_TO_RNA)
  end

  def self.of_rna(rna_to_transcribe)
    rna_to_transcribe.tr(*DNA_TO_RNA.reverse)
  end

end
