class Complement

  TRANSCRIPTION = { "C" => "G", "G" => "C", "T" => "A", "A" => "U" }
  REVERSE_TRANSCRIPTION = TRANSCRIPTION.invert

  def self.of_dna(nucleotides)
    if nucleotides.count("U") > 0
      raise(ArgumentError, "You submitted RNA, this method takes DNA")
    else
      nucleotides.chars.map { |nt| transcribe(nt, TRANSCRIPTION) }.join
  end

  def self.of_rna(nucleotides)
    if nucleotides.count("T") > 0
      raise(ArgumentError, "You submitted DNA, this method takes RNA")
    else
      nucleotides.chars.map { |nt| transcribe(nt, REVERSE_TRANSCRIPTION) }.join
  end

  private

  def self.transcribe(nt, direction)
    direction[nt]
  end

end
