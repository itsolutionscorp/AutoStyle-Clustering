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
    transcribe(strand, RULES)
  end
  
  # Transcribe from RNA to DNA using inverted rules, i.e. backwards
  def self.of_rna(strand)
    transcribe(strand, RULES.invert)
  end
  
  private
  
  def self.transcribe(strand, rules)
    transcription = ""
    
    # Raise an error if the rules don't allow transcription, e.g. if we
    # try to transcribe from DNA to DNA
    strand.chars.each do |item|
      raise ArgumentError unless rules.keys.include?(item)
      
      nucleotide = strand[item]
      transcription << rules[nucleotide]
    end
    
    transcription
  end
end
