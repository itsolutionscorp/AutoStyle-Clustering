class Complement

  DNA_TRANSCRIPTION = { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }
  RNA_TRANSCRIPTION = DNA_TRANSCRIPTION.invert

  def self.of_dna(dna)
    dna.gsub(/#{DNA_TRANSCRIPTION.keys}/, DNA_TRANSCRIPTION)
  end

  def self.of_rna(rna)
    rna.gsub(/#{RNA_TRANSCRIPTION.keys}/, RNA_TRANSCRIPTION)
  end
end
