class Complement

  TRANSCRIPTION = {"G" => "C", "C" => "G", "T" =>"A", "A"=>"U"}

  def self.of_dna(dna)
    transcribe(dna, TRANSCRIPTION)
  end

  def self.of_rna(dna)
    transcribe(dna, TRANSCRIPTION.invert)
  end

  def self.transcribe(dna, transcription)
    dna.chars.map{ |a| transcription[a] }.join
  end

end
