class Complement
  TRANSCRIPTION = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(strand)
    transcribe(strand) { |nucleotide| TRANSCRIPTION[nucleotide] }
  end

  def self.of_rna(strand)
    transcribe(strand) { |nucleotide| TRANSCRIPTION.key(nucleotide) }
  end

  def self.transcribe(strand, &block)
    strand.chars.map do |nucleotide|
      block.call(nucleotide) || (raise ArgumentError)
    end.join
  end
end
