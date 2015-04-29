class Complement

  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna_to_transcribe)
    dna_to_transcribe.chars.map {|letter| DNA_TO_RNA[letter]}.join('')
  end

  def self.of_rna(rna_to_transcribe)
    rna_to_transcribe.chars.map {|letter| DNA_TO_RNA.key(letter)}.join('')
  end

end
