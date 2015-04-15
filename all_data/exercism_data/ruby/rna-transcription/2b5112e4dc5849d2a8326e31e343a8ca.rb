class Complement
  COMPLEMENTS_OF_DNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  COMPLEMENTS_OF_RNA = COMPLEMENTS_OF_DNA.invert

  def self.of_dna(dna_strand)
    transcribe( dna_strand, COMPLEMENTS_OF_DNA )
  end

  def self.of_rna(rna_strand)
    transcribe( rna_strand, COMPLEMENTS_OF_RNA )
  end

  private

  def self.transcribe(strand, transcription_hash)
    strand.chars.map { |nucleotide| transcription_hash[nucleotide] }.join
  end

end
