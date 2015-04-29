class Complement
  
  @@transcript = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  
  def self.of_dna(dna)
    dna.chars.map { |c| @@transcript[c] }.join
  end
  
  def self.of_rna(rna)
    transcript_inv = @@transcript.invert
    rna.chars.map { |c| transcript_inv[c] }.join
  end
end
