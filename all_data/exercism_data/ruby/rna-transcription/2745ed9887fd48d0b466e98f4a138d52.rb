class Complement

  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(dna_to_transcribe)
    dna_to_transcribe.chars.map {|letter| DNA_TO_RNA[letter]}.join('')
  end

  def self.of_rna(rna_to_transcribe)
    rna_to_transcribe.chars.map {|letter| RNA_TO_DNA[letter]}.join('')
  end

end
