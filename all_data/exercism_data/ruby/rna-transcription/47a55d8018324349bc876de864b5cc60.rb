class Complement
  TRANSCRIPTION_LOOKUP = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(nucleotides)
    transcribe(nucleotides) { |nucleotide| TRANSCRIPTION_LOOKUP[nucleotide] }
  end

  def self.of_rna(nucleotides)
    transcribe(nucleotides) { |nucleotide| TRANSCRIPTION_LOOKUP.rassoc(nucleotide).first }
  end

  private

  def self.transcribe(nucleotides, &transcription_method)
    nucleotides.chars.inject('') do |transcription, nucleotide|
      transcription << yield(nucleotide)
    end
  end
end
