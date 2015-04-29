class Complement
  TRANSCRIPTION_LOOKUP = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna)
    transcribe(dna) { |nucleotide| TRANSCRIPTION_LOOKUP[nucleotide] }
  end

  def self.of_rna(rna)
    transcribe(rna) { |nucleotide| TRANSCRIPTION_LOOKUP.key(nucleotide) }
  end

  private

  def self.transcribe(nucleotides, &transcription_method)
    nucleotides.chars.map { |nucleotide| yield(nucleotide) }.join
  end
end
