class Complement
  TRANSCRIPTIONS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  
  # Transcribe from DNA to RNA using the transcirption rules
  def self.of_dna(strand)
    transcribe(strand, TRANSCRIPTIONS)
  end
  
  # Transcribe from RNA to DNA using inverted rules, i.e. backwards
  def self.of_rna(strand)
    transcribe(strand, TRANSCRIPTIONS.invert)
  end
  
  private
  
  def self.transcribe(strand, rules)
    strand.chars.each_with_object("") do |nucleotide, transcription|
      # Raise error in block instead of setting it as the second argument
      # because otherwise it gets appended to our output string
      transcription << rules.fetch(nucleotide) { raise ArgumentError }
    end
  end
end
