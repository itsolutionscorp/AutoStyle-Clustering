class Complement
  DNA_PAIRS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(sequence)
    transcribe(sequence) { |nucleotide| DNA_PAIRS[nucleotide] }
  end

  def self.of_rna(sequence)
    transcribe(sequence) { |nucleotide| DNA_PAIRS.key(nucleotide) }
  end

  def self.transcribe(sequence, &block)
    sequence.chars.map(&block).join
  end
end
