class Complement

  DNA_COMPLEMENTS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna( dna_strand )
    transcribe( dna_strand, DNA_COMPLEMENTS )
  end

  def self.of_rna( rna_strand )
    transcribe( rna_strand, DNA_COMPLEMENTS.invert )
  end

  private

  def self.transcribe( strand, complement_map )
    strand.chars.map { |nucleotide|
      complement_map[ nucleotide ]
    }.join
  end

end
