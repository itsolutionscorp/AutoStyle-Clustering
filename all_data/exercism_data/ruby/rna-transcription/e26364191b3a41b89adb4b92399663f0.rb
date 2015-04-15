class Complement
  DNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  RNA = DNA.invert

  def self.of_dna(sequence)
    transcribe(sequence, DNA)
  end

  def self.of_rna(sequence)
    transcribe(sequence, RNA)
  end

  def self.transcribe(sequence, type)
    sequence.chars.map { |nucleotide| type[nucleotide] }.join
  end
end
