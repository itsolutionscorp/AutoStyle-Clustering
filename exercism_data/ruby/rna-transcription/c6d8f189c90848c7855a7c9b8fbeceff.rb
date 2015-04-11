class Complement

  TRANSCRIPTION_OF_DNA = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  TRANSCRIPTION_OF_RNA = TRANSCRIPTION_OF_DNA.invert

  def self.complement strand, transcription
    complement = ''
    strand.chars.each {|nucleotide|
      complement << transcription[nucleotide]
    }
    complement
  end

  def self.of_dna dna
    complement dna, TRANSCRIPTION_OF_DNA
  end
  def self.of_rna rna
    complement rna, TRANSCRIPTION_OF_RNA
  end
end
