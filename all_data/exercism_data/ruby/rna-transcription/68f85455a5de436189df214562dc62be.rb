class Complement
  # Define rules for transcription
  RULES = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  
  # Transcribe from DNA to RNA using the transcirption rules
  def self.of_dna(strand)
    self.transcribe(strand, RULES)
  end
  
  # Transcribe from RNA to DNA using inverted rules, i.e. backwards
  def self.of_rna(strand)
    self.transcribe(strand, RULES.invert)
  end
  
  private
  
  # Actually transcribe the strand
  def self.transcribe(strand, rules)
    transcription = ""
    
    # Transcribe each nucleotide within our strand according to the rules
    # Raise an error if the rules don't allow transcription, e.g. if we
    # try to transcribe from RNA to RNA or DNA to DNA
    strand.chars.each do |item|
      raise ArgumentError unless rules.keys.include?(item)
      
      transcription << rules[strand[item]]
    end
    
    transcription
  end
end
