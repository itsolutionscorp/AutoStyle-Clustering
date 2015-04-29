class Complement
  DNA_TRANSCRIPTION = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  RNA_TRANSCRIPTION = {
    "C" => "G",
    "G" => "C",
    "A" => "T",
    "U" => "A"
  }

  def self.of_dna(rna_strand)
    rna_strand.gsub(/#{DNA_TRANSCRIPTION.keys}/, DNA_TRANSCRIPTION)
  end

  def self.of_rna(dna_strand)
    dna_strand.gsub(/#{RNA_TRANSCRIPTION.keys}/, RNA_TRANSCRIPTION)
  end
end
