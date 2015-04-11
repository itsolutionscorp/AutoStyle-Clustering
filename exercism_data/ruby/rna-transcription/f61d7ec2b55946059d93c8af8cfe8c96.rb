class Complement
  TRANSCRIBE_RNA = { 'U' => 'A',
    'C' => 'G',
    'G' => 'C',
    'A' => 'T' }

  TRANSCRIBE_DNA = { 'A' => 'U',
    'C' => 'G',
    'G' => 'C',
    'T' => 'A' }

  def self.of_dna(dna_sequence)
    dna_sequence.chars.map { |n| TRANSCRIBE_DNA[n] }.join
  end

  def self.of_rna(rna_sequence)
    rna_sequence.chars.map { |n| TRANSCRIBE_RNA[n] }.join
  end

end
