class Complement
  TRANSCRIPTION = {
    "C" => "G",
    "G" => "C",
    "T" => "A",
    "A" => "U"
  }

  def self.of_dna(strand)
    transcibe(strand, TRANSCRIPTION)
  end

  def self.of_rna(strand)
    transcibe(strand, TRANSCRIPTION.invert)
  end

  def self.transcibe(strand, transcription)
    strand.chars.map { |nucleotide| transcription[nucleotide] }.join
  end
end
