class Complement

  @@dna_to_rna_hash = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna_to_transcribe)
    dna_to_transcribe.chars.map {|letter| @@dna_to_rna_hash[letter]}.join('')
  end

  def self.of_rna(rna_to_transcribe)
    rna_to_transcribe.chars.map {|letter| @@dna_to_rna_hash.invert[letter]}.join('')
  end

end
